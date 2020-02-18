package iti.smb.service.repository;

import iti.smb.service.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByDeletedFalse();
}
