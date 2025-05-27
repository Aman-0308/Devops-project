export class ChangePasswordRequest {
    customerId!: number;
    oldPassword!: string;
    newPassword!: string;
    confirmPassword!: string;
}