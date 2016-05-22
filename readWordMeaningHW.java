

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/** Note: This program is also used to generate aaL.ser for ArrayList2DTest.java
 *  此程式會讀取word_meaning.txt中的單字, 已知每個單元是由Word 1 開始,
 *  但不知道每個單元會有幾個單字, 以及總共會有幾個單元. 所以本題
 *  使用 ArrayList<ArrayList<String>> aaL 來存放每個單元的單字
 *  所以如果要取得第i個單元的第j個單字可以使用 aaL.get(i).get(j)
 * 
 * 每個單字有三個部份:
Word 編號: 單字
(發音)
英文解釋

* 例如:
Word 1: paraphrase
(PAR-uh-frayz)

To restate, put what someone else has expressed into different words.

但本次作業只要抓取單字.
以上個單字而言, 只要抓取 paraphrase

所以你可以判斷一行的開頭是否為"Word "(含空白)來判定是否要抓取該行的內容.
如果是才利用 split(": ")來切割單字.

 * 
 *  請完成以下四個方法
 *  main: 完成讀取檔案並放入aaL的程式, 留意每個放入aaL的aL遇到Word 1時
 *        就要重新配置新的記憶體
 *  showAll: 顯示每個單元下的所有單字
 *  show: 顯示符合查詢字串的所有單元下的所有單字, 並計算個數.
 *   sum: 計算aaL每個單元字數的加總
執行結果如下:
單元1:
advocate delegate unprecedented poignant nebulous clandestine tirade recur tacit allegation 
gullible benign peripheral rebuff animosity tenuous complacent acme defunct abet 
haggard waive carnal sanction ambiguous spendthrift mollify unequivocal malleable verbose 
...
共50個單字.

單元2:
provident impute astute neophyte enigma credence venerate garrulous trenchant autonomous 
...
共50個單字.

單元3:
legerdemain puerile complicity transmute abstruse edify supercilious dissemble vacuous capacious 
...
共50個單字.

...
總共有6個單元.
總共有300個單字.
請輸入欲查詢的開頭字元(直接按Enter結束): a
符合a開頭的單字:
第1個單元有: advocate allegation animosity acme abet ambiguous assuage avaricious allocate analogous 
共有10個單字.
第2個單元有: astute autonomous acquiesce ambivalent attest affinity abject 
共有7個單字.
第3個單元有: abstruse admonish aver 
共有3個單字.
第4個單元有: alacrity acerbic adduce 
共有3個單字.
第5個單元有: apocryphal adventitious 
共有2個單字.
第6個單元有: aleatory apodictic 
共有2個單字.
總共有 27 個單字符合
請輸入欲查詢的開頭字元(直接按Enter結束): x
符合x開頭的單字:
第1個單元有: 
共有0個單字.
第2個單元有: 
共有0個單字.
第3個單元有: 
共有0個單字.
第4個單元有: 
共有0個單字.
第5個單元有: xenophobia 
共有1個單字.
第6個單元有: 
共有0個單字.
總共有 1 個單字符合
請輸入欲查詢的開頭字元(直接按Enter結束): 
BUILD SUCCESSFUL (total time: 25 seconds)
 * @author wfhsi_000
 */

public class readWordMeaningHW {
    static final String file="aaL.ser";
    public static void main(String[] args) {
        File f = new File("word_meaning.txt");
        //System.out.println(f.getAbsolutePath());
       // File f = new File("word_meaning.txt");
        ArrayList<ArrayList<String>> aaL = new ArrayList<>();
        boolean begin = true;
        try {
            Scanner scan = new Scanner(f);
            ArrayList<String> al = new ArrayList<>();
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] tmp = line.split(": ");
                if (tmp[0].startsWith("Word ")) {  // only consider lines start with "Word "
                    if (tmp[0].equals("Word 1")) {   // if it's a first word
                        al = new ArrayList<>();
                        al.add(tmp[1]);
                    } else {
                        al.add(tmp[1]);
                    }
                    if (tmp[0].equals("Word 50")) {
                        aaL.add(al);
                    }
                }                               
            }
            //aaL.add(al);        // the last one should be added back    
            scan.close();
            write2File(aaL);
            aaL=readFromFile();
            
            showAll(aaL);
            query(aaL);
        } catch (FileNotFoundException ex) {
            System.err.printf("reading file error: %s\n", ex.getMessage());
        }
    }

    private static void query(ArrayList<ArrayList<String>> aaL) {      
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("請輸入欲查詢的開頭字元(直接按Enter結束): ");
            String str = scan.nextLine();
            if (str.isEmpty()) break;
            show(aaL, str);
        }
    }

    private static void show(ArrayList<ArrayList<String>> aaL, String str) { 
        int tmp_count = 0;
        int total_count = 0;
        ArrayList tmp_words = new ArrayList();
        for(int i=1 ; i <= aaL.size() ; i++){
            tmp_words = new ArrayList();
            tmp_count = 0;
            for(int j=1 ; j <= aaL.get(i-1).size() ; j++){
                if (aaL.get(i-1).get(j-1).startsWith(str)){
                    tmp_words.add(aaL.get(i-1).get(j-1));
                    tmp_count++;
                    total_count++;
                }
            }
            System.out.println("第" + i + "個單元有:" + tmp_words.toString());
            System.out.println("共有" +tmp_count+"個單字");
        }
        System.out.println("總共有:" + total_count+"個單字符合");
    }
    // 計算aaL每個單元字數的加總
    private static int sum(ArrayList<ArrayList<String>> aaL) {
        int total =0;
        for(int i=1 ; i <= aaL.size() ; i++){
            System.out.println("第"+ i +"個單元共有"+ ":" + aaL.get(i-1).size()+"個單字 ");
            total += aaL.get(i-1).size();
        }
        return total;
    }

    // 顯示每個單元的每個單字
    private static void showAll(ArrayList<ArrayList<String>> aaL) {
        for(int i=1 ; i <= aaL.size() ; i++){
            System.out.println("單元" + i + ":");
            for (int j=1 ; j<=aaL.get(i-1).size() ; j++){
                if (j%10 ==0) {
                    System.out.println(aaL.get(i-1).get(j-1));
                } else {
                    System.out.print(aaL.get(i-1).get(j-1)+ " ");
                }
                //System.out.print(aaL.get(i-1).get(j-1) + " ");
            };
            System.out.println("");
        }
        //sum return type : int
        System.out.println("總共有:" + aaL.size()+"個單元");
        System.out.println("總共有:" + sum(aaL)+"個單字");
    }

    // 此方法請不用更動
    private static ArrayList<ArrayList<String>> readFromFile() {
        FileInputStream fin;
        ArrayList<ArrayList<String>> res=null;
        try {
            fin = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fin);
            res = (ArrayList<ArrayList<String>>) ois.readObject();
            ois.close();
        } catch (Exception ex) {
            System.err.printf("output error: %s\n", ex.getMessage());
        }        
        return res;
    }
    
    // 此方法請不用更動
    private static void write2File(ArrayList<ArrayList<String>> aaL) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // modify the content of aaL a little to check whether student's program can still read correctly
            aaL.remove(0);
            aaL.remove(1);
            aaL.remove(2);
            aaL.remove(3);
            oos.writeObject(aaL);
            oos.close();
        } catch (Exception ex) {
            System.err.printf("output error: %s\n", ex.getMessage());
        } 
    }
}
