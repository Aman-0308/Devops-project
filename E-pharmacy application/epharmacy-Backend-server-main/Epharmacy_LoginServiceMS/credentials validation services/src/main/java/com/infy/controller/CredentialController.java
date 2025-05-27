

@RestController
@RequestMapping("/api/credentials")
public class CredentialsController {

    private final CredentialsValidationService credentialsService;

    public CredentialsController(CredentialsValidationService credentialsService) {
        this.credentialsService = credentialsService;
    }

    @PostMapping("/validate")
    public ResponseEntity<LoginResponse> validateCredentials(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = credentialsService.validateCredentials(loginRequest);
        return ResponseEntity.ok(response);
    }
}
