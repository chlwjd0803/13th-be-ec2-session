package project.ec2session.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import project.ec2session.domain.user.dto.UserReq;

@Tag(name = "[인증 관련 API]", description = "인증 관련 API")
public interface AuthApi {

    @Operation(summary = "로그인", description = "로그인 시도")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                            {
                                "accessToken" : "<accessToken>"
                            }
                            """)
                    })),
            @ApiResponse(responseCode = "400", description = "로그인실패, 잘못된 입력",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                            {
                                "fieldName" : "필드는 필수 입력 값입니다."
                            }
                            """)
                    })),
            @ApiResponse(responseCode = "404", description = "로그인실패, 아이디 정보 없음",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                            {
                                "status" : "404",
                                "message" : "정보를 정확히 입력해주세요."
                            }
                            """)
                    })),
            @ApiResponse(responseCode = "404", description = "로그인실패, 잘못된 비밀번호",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                            {
                                "status" : "404",
                                "message" : "잘못된 비밀번호입니다."
                            }
                            """)
                    }))
    })
    ResponseEntity<?> signIn(
            @RequestBody @Valid UserReq.SignInDto request
    );

    @Operation(summary = "회원가입", description = "회원가입 시도")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원가입 성공",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                            {
                                "userId" : "<userId>"
                            }
                            """)
                    })),
            @ApiResponse(responseCode = "400", description = "회원가입 실패",
                    content = @Content(mediaType = "application/json", examples = {
                            @ExampleObject(value = """
                            {
                                "fieldName" : "필드는 필수 입력 값입니다."
                            }
                            """)
                    }))
    })
    ResponseEntity<?> signUp(
            @RequestBody @Valid UserReq.SignUpDto request
    );
}
