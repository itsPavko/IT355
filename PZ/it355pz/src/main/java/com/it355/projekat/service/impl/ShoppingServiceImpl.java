package com.it355.projekat.service.impl;

import com.it355.projekat.entity.Shopping;
import com.it355.projekat.entity.Wallet;
import com.it355.projekat.entity.Watch;
import com.it355.projekat.entity.Member;
import com.it355.projekat.entity.enums.MemberType;
import com.it355.projekat.exception.NotEnoughMoneyException;
import com.it355.projekat.repository.ShoppingRepository;
import com.it355.projekat.repository.abstractrep.AbstractRepository;
import com.it355.projekat.security.SecurityUtil;
import com.it355.projekat.service.WatchService;
import com.it355.projekat.service.MemberService;
import com.it355.projekat.service.ShoppingService;
import com.it355.projekat.service.generic.impl.GenericServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ShoppingServiceImpl extends GenericServiceImpl<Shopping> implements ShoppingService {

    private final MemberService memberService;
    private final WatchService watchService;

    protected ShoppingServiceImpl(AbstractRepository<Shopping> abstractRepository, MemberService memberService, WatchService watchService) {
        super(abstractRepository);
        this.memberService = memberService;
        this.watchService = watchService;
    }

    @Override
    public Shopping saveShopping(Integer userId, Integer watchId) {
        Member member = memberService.findByUserId(userId);
        Watch watch = watchService.findById(watchId);

        if (member.getType() == MemberType.PREMIUM) {
            watch.setPrice(watch.getPrice() - (watch.getPrice() * member.getDiscount() / 100));
        }

        double purchasePrice = watch.getPrice();

        if (member.getUser().getWallet().getMoney() >= purchasePrice) {
            watch.setAmount(watch.getAmount() - 1);
            member.getUser().getWallet().setMoney(
                    member.getUser().getWallet().getMoney() - watch.getPrice()
            );

            watchService.save(watch);
            memberService.save(member);

            return abstractRepository.save(new Shopping(
                    watch,
                    member,
                    LocalDate.now(),
                    purchasePrice
            ));

        } else {
            throw new NotEnoughMoneyException();
        }
    }

    @Override
    public List<Shopping> findAllByLoggedInMember() {
        String username = SecurityUtil.getSessionUser();
        Member member = memberService.findByUsername(username);
        return ((ShoppingRepository) abstractRepository).findAllByMemberId(member.getId());
    }
}
