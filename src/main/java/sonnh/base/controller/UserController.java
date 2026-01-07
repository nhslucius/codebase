package sonnh.base.controller;
//
//import io.swagger.v3.oas.annotations.Operation;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/users")
//@RequiredArgsConstructor
//public class UserController {
//
//    private final UserService userService;
//
//    @PostMapping
//    @Operation(summary = "Create user")
//    public ApiResponse<User> create(@Valid @RequestBody UserRequest request) {
//        return ApiResponse.success(userService.create(request));
//    }
//
//    @GetMapping
//    @Operation(summary = "Get all users")
//    public ApiResponse<List<User>> getAll() {
//        return ApiResponse.success(userService.getAll());
//    }
//}
