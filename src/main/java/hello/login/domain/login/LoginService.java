package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * @return null 로그인 실패
     */
    public Member login(String loginId, String password) {
//        Optional<Member> findMemberOptional = memberRepository.findByLoginId(loginId);//회원 있는지 조회
//        Member member = findMemberOptional.get();//데이터 꺼낼 수 있다. 없으면 예외가 터짐
//        if (member.getPassword().equals(password)) {
//            return member;
//        } else {
//            return null;
//        }

        //아래와 같이 위 코드들을 간략하게 줄일 수 있다.! JAVA 8의 람다가 있기때문이지!!!
        return memberRepository.findByLoginId(loginId) //MemberRepository 에서 id 조회 먼저 하고
                .filter(m -> m.getPassword().equals(password)) //filter 추가 비밀번호 맞는지 확인 맞다면 해당 Member 데이터 반환
                .orElse(null); //틀리다면 null을 반환

    }

}
