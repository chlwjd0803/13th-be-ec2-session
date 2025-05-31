package project.ec2session.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import project.ec2session.common.auth.CustomUserDetails;
import project.ec2session.domain.user.dto.UserReq;

@Tag(name = "[사용자 관련 API]", description = "사용자 관련 API")
public interface UserApi {
    @Operation(summary = "자신의 정보 가져오기", description = "토큰이 유효할 때 자신의 정보 조회가능")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "확인 성공",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                            {
                                "userId": "<userId>",
                                "username": "<username>",
                                "nickname": "<nickname>"
                            }
                            """)
                    })),
            @ApiResponse(responseCode = "403", description = "토큰 인증 실패, 토큰 없음",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                            {
                                "timestamp": "<timestamp>",
                                "status": 403,
                                "error": "Forbidden",
                                "path": "/api/v1/users/me"
                            }
                            """)
                    }))
    })
    ResponseEntity<?> getUserInfo(@AuthenticationPrincipal CustomUserDetails userDetails);

    @Operation(summary = "모든 사용자정보 가져오기", description = "토큰이 유효할 때 모든 사용자정보 조회가능")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "확인 성공",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                            {
                                "userId": "<userId>",
                                "username": "<username>",
                                "nickname": "<nickname>"
                            }
                            {
                                "userId": "<userId>",
                                "username": "<username>",
                                "nickname": "<nickname>"
                            }
                            ...
                            """)
                    })),
            @ApiResponse(responseCode = "403", description = "토큰 인증 실패, 토큰 없음",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                            {
                                "timestamp": "<timestamp>",
                                "status": 403,
                                "error": "Forbidden",
                                "path": "/api/v1/users"
                            }
                            """)
                    }))
    })
    ResponseEntity<?> getAllUser();


    @Operation(summary = "사용자 닉네임 수정", description = "토큰이 유효하고, 수정할 닉네임 입력")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "400", description = "수정할 닉네임 없음",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                            {
                                "nickname" : "닉네임은 필수 입력 값입니다."
                            }
                            """)
                    })),
            @ApiResponse(responseCode = "403", description = "토큰 인증 실패, 토큰 없음",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                            {
                                "timestamp": "<timestamp>",
                                "status": 403,
                                "error": "Forbidden",
                                "path": "/api/v1/users"
                            }
                            """)
                    }))
    })
    ResponseEntity<?> updateUserInfo(@AuthenticationPrincipal CustomUserDetails userDetails,
                                     @RequestBody @Valid UserReq.UpdateInfo request);
}
