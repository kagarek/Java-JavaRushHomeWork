package com.javarush.test.level31.lesson06.home01;

/**
 * Created by Igor_Makarychev on 02.12.2015.
 */
public class RunSolution
{
    public static void main(String[] args)
    {
        String[] input = new String[2];
        input[0] = "resources/level31_lesson06_home01/new_file3.txt";


        input[1]="resources/level31_lesson06_home01/Archive_withFileInNew.zip";
        Solution.main(input);

        input[1]="resources/level31_lesson06_home01/Archive_withFileNotInNew.zip";
        Solution.main(input);

        input[1]="resources/level31_lesson06_home01/Archive_withoutFile.zip";
        Solution.main(input);
    }
}
