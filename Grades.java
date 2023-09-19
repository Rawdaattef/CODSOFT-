import java.util.Scanner;

public class Grades {
    public static void main(String[] args) {
        System.out.print("Enter number of grades: ");
        Scanner sc = new Scanner(System.in);
        int subCnt = sc.nextInt();

        float totalGrade=0;
        for(int i=0 ; i<subCnt ; i++)
        {
            System.out.print("Enter grade #" + (i+1) +": ");
            float grade=sc.nextFloat();
            totalGrade+=grade;
        }

        float avgPer=totalGrade/subCnt;
        char finalGrade;
        if(avgPer>=90) finalGrade='A';
        else if(avgPer>=80) finalGrade='B';
        else if(avgPer>=70) finalGrade='C';
        else if(avgPer>=60) finalGrade='D';
        else finalGrade='F';

        System.out.println("The total marks obtained in all subjects: " +totalGrade);
        System.out.println("The average percentage: " +avgPer);
        System.out.println("The final grade: " +finalGrade);

    }
}
