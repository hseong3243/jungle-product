package com.hseong.jungleproduct.auth;

import com.hseong.jungleproduct.domain.MemberRole;
import java.util.List;

public record ClaimsResponse(Long memberId, MemberRole memberRole, List<String> authorities) {

}
