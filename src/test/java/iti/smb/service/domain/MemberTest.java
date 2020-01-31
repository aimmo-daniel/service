package iti.smb.service.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberTest {

    @Test
    void create() {
        Member member = Member.builder()
                .id(1L)
                .name("sangjin")
                .build();

        assertThat(member.getId()).isEqualTo(1L);
        assertThat(member.getName()).isEqualTo("sangjin");
    }

}