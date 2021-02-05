import java.util.*;

class Patterns{

    private static Scanner sc;

    public static void pattern1(int n){
        for(int i = 0; i < n; i++){
            for(int j = 0; j <= i; j++) System.out.print("*\t");
            System.out.println();
        }
    }

    public static void pattern2(int n){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n - i; j++) System.out.print("*\t");
            System.out.println();
        }
    }

    public static void pattern3(int n){     
        for(int i = 1; i <= n; i++){
            int tabs =  n - i;
            int stars = i;
            for(int j = 0; j < tabs; j++) System.out.print("\t");
            for(int j = 0; j < stars; j++) System.out.print("*\t");
            System.out.println();
        }
    }

    public static void pattern4(int n){
         
        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++) System.out.print("\t");
            for(int j = 0; j < n - i; j++) System.out.print("*\t");
            System.out.println();
        }
    }

    public static void pattern5(int n){
        int star = 1;
        int space = n / 2;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= space; j++) System.out.print("\t");
            for(int j = 1; j <= star; j++) System.out.print("*\t");
            if(i <= n/2){
                space--;
                star+=2;
            }else{
                space++;
                star-=2;
            }
            System.out.println();
        }
    }

    public static void pattern6(int n){
        int space = 1;
        int star = (n + 1)/2;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= star; j++) System.out.print("*\t");
            for(int j = 1; j <= space; j++) System.out.print("\t");
            for(int j = 1; j <= star; j++) System.out.print("*\t");
            if(i <= n/2){
                space+=2;
                star--;
            }else{
                space-=2;
                star++;
            }
            System.out.println();
        }
    }

    public static void pattern7(int n){
         
        for(int i = 0; i < n; i++){
            for(int j = 0; j <= i; j++){
                if(i == j) System.out.print("*\t");
                else System.out.print("\t");
            }
            System.out.println();
        }
    }

    public static void pattern8(int n){
         
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i + j == n - 1) System.out.print("*\t");
                else System.out.print("\t");
            }
            System.out.println();
        }
    }

    public static void pattern9(int n) {
     
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i == j) System.out.print("*\t");
                else if (i + j == n - 1) System.out.print("*\t");
                else System.out.print("\t");
            }
            System.out.println();
        }
    }

    public static void pattern10(int n){
        // outer spaces
        int os = n / 2;
        // inner spaces
        int is = -1;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= os; j++) System.out.print("\t");
            System.out.print("*\t");
            for(int j = 1; j <= is; j++) System.out.print("\t");
            if(i > 1 && i < n) System.out.print("*\t");
            if(i <= n/2){
                os--;
                is += 2;
            }else{
                os++;
                is -= 2;
            }
            System.out.println();
        }
    }

    public static void pattern11(int n){
        int a = 1;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= i; j++) System.out.print(a++ + "\t");
            System.out.println();
        }
    }

    public static void pattern12(int n){
        int a = 0, b = 1;
        int c = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= i; j++){
                System.out.print(c + "\t");
                a = b;
                b = c;
                c = a + b;
            }
            System.out.println();
        }
    }

    public static void pattern13(int n){
        
        for(int i = 0; i < n; i++){
            int a = 1;
            for(int j = 0; j <= i; j++){
                System.out.print(a + "\t");
                int temp = a * (i - j)/(j + 1);
                a = temp;
            }
            System.out.println();
        }
    }

    // Pattern 14
    public static void printTableOfN(int n){
        for(int i = 1;i <= 10;i++){
            System.out.println(n + " * " +  i + " = " + n * i);
        }
    }

    public static void pattern15(int n){
        int val = 1;
        int valLim = 1;
        int space = n / 2;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= space; j++) System.out.print("\t");
            int count = 0;
            for(int j = 1; j <= valLim; j++){
                System.out.print(val + count + "\t");
                if(j <= valLim/2) count++;
                else count--;
            }
            if(i <= n/2){
                space--;
                val++;
                valLim+=2;
            }else{
                space++;
                val--;
                valLim-=2;
            }
            System.out.println();
        }
    }

    public static void pattern16(int n){
        int st = 1; 
        int sp = 2*n - 3;
        for(int i = 1; i <= n; i++){
            int val = 1;
            for(int j = 1; j <= st; j++) System.out.print(val++ + "\t");
            for(int j = 1; j <= sp; j++) System.out.print("\t");
            if(i == n){
                st--;
                val--;
            }
            for(int j = 1; j <= st; j++) System.out.print(--val + "\t");
            System.out.println();
            st++;
            sp-=2;
        }
    }

    public static void pattern17(int n){
        int sp = n/2;
        int st = 1;
        String ch = "";
        for(int i = 1; i <= n; i++){
            if(i == n/2 + 1) ch = "*";
            else ch = "";
            for(int j = 1; j <= sp; j++) System.out.print(ch + "\t");
            for(int j = 1; j <= st; j++) System.out.print("*\t");
            if(i <= n/2) st++;
            else st--;
            System.out.println();
        }
    }

    public static void pattern18(int n){
        int st = n;
        int sp = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= sp; j++) System.out.print("\t");
            for(int j = 1; j <= st; j++){
                if(i > 1 && i <= n/2 && j > 1 && j < st){
                    System.out.print("\t");
                    continue;
                }
                System.out.print("*\t");
            }
            if(i <= n/2){
                st -= 2;
                sp++;
            }else{
                st += 2;
                sp--;
            }
            System.out.println();
        }
    }

    public static void pattern19(int n){
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(i == 1){
                    if(j == n || j <= n/2 + 1)
                        System.out.print("*\t");
                    else System.out.print("\t");
                }else if(i <= n/2){
                    if(j == n || j == n/2 + 1) System.out.print("*\t");
                    else System.out.print("\t");
                }else if(i == n/2 + 1){
                    System.out.print("*\t");
                }else if(i < n){
                    if(j == 1 || j == n/2 + 1) System.out.print("*\t");
                    else System.out.print("\t");
                }else{
                    if(j == 1 || j >= n/2 + 1) System.out.print("*\t");
                    else System.out.print("\t");
                }
            }
            System.out.println();
        }     
    }

    public static void pattern20(int n){
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(j == 1 || j == n || (i > n/2 && (i == j || i + j == n + 1))) System.out.print("*\t");
                else System.out.print("\t");
            }
            System.out.println();
        }
    }

    public static void anon1(int n) {
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int sum = i + j;
                if(sum == n - 3 || sum == n - 1 || sum == n + 1) System.out.print("*\t");
                else System.out.print("\t"); 
            }
            System.out.println();
        }
    }

    public static void anon2(int n) {
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(i == j || i == j - 2 || i == j + 2) System.out.print("*\t");
                else System.out.print("\t"); 
            }
            System.out.println();
        }
    }

    public static void anon3(int n){
        int val = 1;
        int valLim = 1;
        int space = n / 2;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= space; j++) System.out.print("\t");
            int count = 0;
            for(int j = 1; j <= valLim; j++){
                System.out.print(val + count + "\t");
                if(j <= valLim/2) count--;
                else count++;
            }
            if(i <= n/2){
                space--;
                val++;
                valLim+=2;
            }else{
                space++;
                val--;
                valLim-=2;
            }
            System.out.println();
        }
    }


    public static void main(String[] args){
        sc = new Scanner(System.in);
        int n = sc.nextInt();
        pattern1(n);

    }
}