import java.util.Scanner;
import java.util.InputMismatchException;
public class Matrix {
    private Complex[][] mat;
    public Matrix(){
        this.mat=new Complex[1][1];
        this.mat[0][0]=new Complex(0,0);

    }
    public Matrix(int n){
        this.mat = new Complex[n][n];
    }
    public Matrix(Complex[][] matrix){
        this.mat=matrix;
    }

    public Matrix(int n, int num){
        Scanner scan=new Scanner(System.in);
        this.mat=new Complex[n][n];
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                while (true){
                    try {
                        System.out.println("Enter valid and imaginary parts of " + i + " " + j + " element of " + num + " matrix");
                        Complex a=new Complex(scan.nextDouble(), scan.nextDouble());
                        this.mat[i][j]=a;
                        break;
                    }
                    catch (InputMismatchException e){
                        System.out.println("Enter 2 double numbers please");
                        scan.nextLine();
                    }
                }
            }
        }
    }
    public void print_matrix(int n){
        for (int i=0; i<n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(this.mat[i][j].toString());
                System.out.print("\t");
            }
            System.out.print("\n");
        }
    }
    public void summary_matrix(Matrix mat2, int n, char sign){
        Matrix tmp=new Matrix(n);
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                tmp.mat[i][j]=new Complex();
                if (sign=='+'){
                    tmp.mat[i][j]=this.mat[i][j].sum(mat2.mat[i][j]);
                }
                else{
                    tmp.mat[i][j]=this.mat[i][j].sub(mat2.mat[i][j]);
                }
            }
        }
        tmp.print_matrix(n);
    }

    public void multiplication(Matrix mat2, int n){
        Matrix tmp_arr=new Matrix(n);
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                double val=0, im=0;
                for (int k=0; k<n; k++){
                    if (this.mat[i][k] != null && mat2.mat[k][j] != null) {
                        Complex t = new Complex();
                        t = this.mat[i][k].mul_res(mat2.mat[k][j]);
                        val += t.getValid();
                        im += t.getImage();
                    }
                }
                tmp_arr.mat[i][j]=new Complex(val, im);
            }
        }
        tmp_arr.print_matrix(n);
    }
    public Complex determinant (int n){
        if (n==1){
            return this.mat[0][0];
        }
        else if (n==2){
            Complex mul1=this.mat[0][0].mul_res(this.mat[1][1]);
            Complex mul2=this.mat[0][1].mul_res(this.mat[1][0]);
            return mul1.sub(mul2);
        }
        else{
            Complex det=new Complex();
            for (int j=0; j<n; j++){
                Matrix temp=new Matrix(n-1);
                int ti=0;
                for (int i=1; i<n; i++){
                    int tj=0;
                    for (int c=0; c<n; c++){
                        if (c!=j){
                            temp.mat[ti][tj]=this.mat[i][c];
                            tj++;
                        }
                    }
                    ti++;
                }
                Complex temp_deter=temp.determinant(n-1);
                Complex a=this.mat[0][j].mul_res(temp_deter);
                if ((j+1)%2==0){
                    a.setValid(-a.getValid());
                    a.setImage(-a.getImage());
                }
                det.setValid(a.getValid()+det.getValid());
                det.setImage(a.getImage()+det.getImage());
            }
            return det;
        }
    }
    public Matrix trans (int n){
        Complex[][] tmp=new Complex[n][n];
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                tmp[i][j]=this.mat[j][i];
            }
        }
        return new Matrix(tmp);
    }
    public Matrix inverse(int n){
        try{
            Matrix mtr=new Matrix(this.mat);
            Complex deter=mtr.determinant(n);
            if (deter.getValid()==0 && deter.getImage()==0){
                return null;
            }
            else {
                Matrix minor = new Matrix(n);
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        minor.mat[i][j] = new Complex();
                        Complex[][] temp = new Complex[n - 1][n - 1];
                        int ii = 0;
                        for (int i_temp = 0; i_temp < n; i_temp++) {
                            int jj = 0;
                            if (i_temp == i)
                                continue;
                            for (int j_temp = 0; j_temp < n; j_temp++) {
                                if (j_temp == j) {
                                    continue;
                                }
                                temp[ii][jj] = new Complex();
                                temp[ii][jj] = this.mat[i_temp][j_temp];
                                jj++;
                            }
                            ii++;
                        }
                        minor.mat[i][j] = determinant(n - 1);
                        if ((i + j) % 2 != 0) {
                            minor.mat[i][j].setValid(-minor.mat[i][j].getValid());
                            minor.mat[i][j].setImage(-minor.mat[i][j].getImage());
                        }
                    }
                }
                Matrix minor_2 = minor.trans(n);
                Complex[][] inverse = new Complex[n][n];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        inverse[i][j] = new Complex();
                        inverse[i][j] = minor_2.mat[i][j].divide(deter);
                    }
                }
                return new Matrix(inverse);
            }
        }
        catch (NullPointerException pe){
            System.out.println("Determinant is 0. This matrix does not have inverse matrix.");
            return null;
        }
    }
}

