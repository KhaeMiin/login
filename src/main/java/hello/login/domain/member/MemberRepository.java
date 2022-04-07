package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); //static 사용
    private static long sequence = 0L; //static 사용

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
//        List<Member> all = findAll();
//        for (Member m : all) {
//            if (m.getLoginId().equals(loginId)) {
//                return Optional.of(m);
//            }
//        }
//        return Optional.empty();


        return findAll().stream() //List에서
                .filter(m -> m.getLoginId().equals(loginId)) //filter() 에 만족하는 값만 다음단계로 넘어가고 만족하지 않은 값은 버려짐
                .findFirst(); //먼저 나오는 값을 반환시킨다.
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
