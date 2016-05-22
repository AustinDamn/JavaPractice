

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/** Note: This program is also used to generate aaL.ser for ArrayList2DTest.java
 *  ���{���|Ū��word_meaning.txt������r, �w���C�ӳ椸�O��Word 1 �}�l,
 *  �������D�C�ӳ椸�|���X�ӳ�r, �H���`�@�|���X�ӳ椸. �ҥH���D
 *  �ϥ� ArrayList<ArrayList<String>> aaL �Ӧs��C�ӳ椸����r
 *  �ҥH�p�G�n���o��i�ӳ椸����j�ӳ�r�i�H�ϥ� aaL.get(i).get(j)
 * 
 * �C�ӳ�r���T�ӳ���:
Word �s��: ��r
(�o��)
�^�����

* �Ҧp:
Word 1: paraphrase
(PAR-uh-frayz)

To restate, put what someone else has expressed into different words.

�������@�~�u�n�����r.
�H�W�ӳ�r�Ө�, �u�n��� paraphrase

�ҥH�A�i�H�P�_�@�檺�}�Y�O�_��"Word "(�t�ť�)�ӧP�w�O�_�n����Ӧ檺���e.
�p�G�O�~�Q�� split(": ")�Ӥ��γ�r.

 * 
 *  �Ч����H�U�|�Ӥ�k
 *  main: ����Ū���ɮרé�JaaL���{��, �d�N�C�ө�JaaL��aL�J��Word 1��
 *        �N�n���s�t�m�s���O����
 *  showAll: ��ܨC�ӳ椸�U���Ҧ���r
 *  show: ��ܲŦX�d�ߦr�ꪺ�Ҧ��椸�U���Ҧ���r, �íp��Ӽ�.
 *   sum: �p��aaL�C�ӳ椸�r�ƪ��[�`
���浲�G�p�U:
�椸1:
advocate delegate unprecedented poignant nebulous clandestine tirade recur tacit allegation 
gullible benign peripheral rebuff animosity tenuous complacent acme defunct abet 
haggard waive carnal sanction ambiguous spendthrift mollify unequivocal malleable verbose 
...
�@50�ӳ�r.

�椸2:
provident impute astute neophyte enigma credence venerate garrulous trenchant autonomous 
...
�@50�ӳ�r.

�椸3:
legerdemain puerile complicity transmute abstruse edify supercilious dissemble vacuous capacious 
...
�@50�ӳ�r.

...
�`�@��6�ӳ椸.
�`�@��300�ӳ�r.
�п�J���d�ߪ��}�Y�r��(������Enter����): a
�ŦXa�}�Y����r:
��1�ӳ椸��: advocate allegation animosity acme abet ambiguous assuage avaricious allocate analogous 
�@��10�ӳ�r.
��2�ӳ椸��: astute autonomous acquiesce ambivalent attest affinity abject 
�@��7�ӳ�r.
��3�ӳ椸��: abstruse admonish aver 
�@��3�ӳ�r.
��4�ӳ椸��: alacrity acerbic adduce 
�@��3�ӳ�r.
��5�ӳ椸��: apocryphal adventitious 
�@��2�ӳ�r.
��6�ӳ椸��: aleatory apodictic 
�@��2�ӳ�r.
�`�@�� 27 �ӳ�r�ŦX
�п�J���d�ߪ��}�Y�r��(������Enter����): x
�ŦXx�}�Y����r:
��1�ӳ椸��: 
�@��0�ӳ�r.
��2�ӳ椸��: 
�@��0�ӳ�r.
��3�ӳ椸��: 
�@��0�ӳ�r.
��4�ӳ椸��: 
�@��0�ӳ�r.
��5�ӳ椸��: xenophobia 
�@��1�ӳ�r.
��6�ӳ椸��: 
�@��0�ӳ�r.
�`�@�� 1 �ӳ�r�ŦX
�п�J���d�ߪ��}�Y�r��(������Enter����): 
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
            System.out.print("�п�J���d�ߪ��}�Y�r��(������Enter����): ");
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
            System.out.println("��" + i + "�ӳ椸��:" + tmp_words.toString());
            System.out.println("�@��" +tmp_count+"�ӳ�r");
        }
        System.out.println("�`�@��:" + total_count+"�ӳ�r�ŦX");
    }
    // �p��aaL�C�ӳ椸�r�ƪ��[�`
    private static int sum(ArrayList<ArrayList<String>> aaL) {
        int total =0;
        for(int i=1 ; i <= aaL.size() ; i++){
            System.out.println("��"+ i +"�ӳ椸�@��"+ ":" + aaL.get(i-1).size()+"�ӳ�r ");
            total += aaL.get(i-1).size();
        }
        return total;
    }

    // ��ܨC�ӳ椸���C�ӳ�r
    private static void showAll(ArrayList<ArrayList<String>> aaL) {
        for(int i=1 ; i <= aaL.size() ; i++){
            System.out.println("�椸" + i + ":");
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
        System.out.println("�`�@��:" + aaL.size()+"�ӳ椸");
        System.out.println("�`�@��:" + sum(aaL)+"�ӳ�r");
    }

    // ����k�Ф��Χ��
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
    
    // ����k�Ф��Χ��
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
