import java.util.Scanner;
import java.util.InputMismatchException;
public class Main {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        int n;
        while (true){
            try {
                System.out.println("Enter length of matrix:");
                n = scan.nextInt();
                break;
            }
            catch (InputMismatchException e){
                System.out.println("Enter integer number please");
                scan.nextLine();
            }
        }
        Matrix matrix_1 = new Matrix(n, 1);
        Matrix matrix_2 = new Matrix(n, 2);
        System.out.print("The first matrix:\n");
        matrix_1.print_matrix(n);
        System.out.print("The second matrix:\n");
        matrix_2.print_matrix(n);
        System.out.print("The result of summary of matrix:\n");
        matrix_1.summary_matrix(matrix_2, n, '+');
        System.out.print("The result of subtraction of matrix:\n");
        matrix_1.summary_matrix(matrix_2, n, '-');
        System.out.println("The result of multiplication:");
        matrix_1.multiplication(matrix_2, n);
        System.out.println("The result of determinant 1 matrix: ");
        Complex det1 = matrix_1.determinant(n);
        System.out.print(det1.toString());
        System.out.println("\nThe result of determinant 2 matrix: ");
        Complex det2 = matrix_2.determinant(n);
        System.out.print(det2.toString());
        System.out.println("\nThe result of dividing matrix: ");
        try {
            matrix_1.multiplication(matrix_2.inverse(n), n);
        } catch (NullPointerException pe) {
            System.out.println("Determinant is 0. This matrix does not have inverse matrix.");
        }
    }
}