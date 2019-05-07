package medicine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import javax.swing.JOptionPane;
import java.io.File;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import javax.swing.Timer;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Design extends javax.swing.JFrame {

    private Timer timer;
    int countLines = 0;
    int countEngLines = 0;
    String path;
    String pathEng;

    {
        try {
            System.setProperty("file.encoding", "UTF-8");
            Field charset = Charset.class.getDeclaredField("defaultCharset");
            charset.setAccessible(true);
            charset.set(null, null);

            File nameFile = new File("questionsUKR.txt");
            path = nameFile.getAbsolutePath();

            File file = new File(path);
            FileReader fr = new FileReader(file);
            try (BufferedReader reader = new BufferedReader(fr)) {
                String lines = "";

                while (lines != null) {
                    lines = reader.readLine();
                    countLines++;
                }
                countLines++;
            }

            File nameFileEng = new File("questionsENG.txt");
            pathEng = nameFileEng.getAbsolutePath();

            File fileEng = new File(pathEng);
            FileReader frEng = new FileReader(fileEng);
            try (BufferedReader reader2 = new BufferedReader(frEng)) {
                String lines = "";

                while (lines != null) {
                    lines = reader2.readLine();
                    countEngLines++;
                }
                countEngLines++;
            }

        } 
        catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found exception");
        } 
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "IOException");
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception 57");
        }
    }

    int index = 1;
    int countqe = 0;
    int countqeEng = 0;
    int number = 0;

    static int countActualQuestions = 0;
    static int CountRight = 0;
    static int CountQuest = 0;

    int[] wrongAnsNumber = new int[(countLines - 1) / 6];
    int counterWrongAnsNumber = 0;

    int counterCurrentAnswers = 0;
    int[] currentAnswers = new int[(countLines - 1) / 6];

    {
        for (int k = 0; k < wrongAnsNumber.length; k++) {
            wrongAnsNumber[k] = 0;
            currentAnswers[k] = 0;
        }
    }

    String data[][] = new String[countLines][2];
    String dataEng[][] = new String[countEngLines][2];
    String dataUniversal[][] = new String[1212][2];

    {
        for (int k = 0; k < data.length - 2; k++) {
            data[k][1] = "0";
        }
        
        for (int i = 0; i < dataUniversal.length; i++) {
            dataUniversal[i][0] = " ";
            dataUniversal[i][1] = " ";
        }
    }

    int questioncheck[] = new int[(countLines - 1) / 6];
    int questioncheckEng[] = new int[30];

    {
        for (int k = 0; k < questioncheck.length; k++) {
            questioncheck[k] = -1;
        }

        for (int i = 0; i < questioncheckEng.length; i++) {
            questioncheckEng[i] = -1;
        }
    }

    int un1 = 0;
    int un2 = 1;
    int un3 = 2;
    int un4 = 3;
    int un5 = 4;
    int un6 = 5;

    public void makeProgram() {
        try {
            timer = new Timer(1000, new TimerTick());
            timer.start();
            jLabel3.setText((CountQuest + 1) + " з " + 200);
            jLabel4.setText("1:00");
            jRadioButton1.setSelected(false);
            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
            jRadioButton5.setSelected(false);

            if (CountQuest == 200) {
                jLabel1.setText("Ви правильно відповіли на  " + CountRight + " питань з " + 200 + ".");
                timer.stop();
                jButton5.setVisible(true);
                jRadioButton1.setVisible(false);
                jRadioButton2.setVisible(false);
                jRadioButton3.setVisible(false);
                jRadioButton4.setVisible(false);
                jRadioButton5.setVisible(false);
                jButton1.setVisible(false);
                jLabel1.setVisible(true);
                jButton2.setVisible(true);
                jButton3.setVisible(false);
                jLabel2.setVisible(false);
                jLabel3.setVisible(false);
                jLabel4.setVisible(false);

                this.setSize(480, 270);
                this.setLocationRelativeTo(null);
                setLayout(null);
                jLabel1.setSize(350, 35);
                jButton2.setSize(100, 35);
                jButton5.setSize(150, 35);
                jLabel1.setLocation(40, 20);
                jButton2.setLocation(330, 185);
                jButton5.setLocation(165, 185);
                return;
            }

            if (CountQuest < 170) {
                CountQuest++;

                Random rn = new Random();
                number = questioncheck[countqe];

                String quest = data[questioncheck[countqe] * 6][0];

                String answer1 = data[number * 6 + 1][0];
                String answer2 = data[number * 6 + 2][0];
                String answer3 = data[number * 6 + 3][0];
                String answer4 = data[number * 6 + 4][0];
                String answer5 = data[number * 6 + 5][0];

                String rndanswers[] = new String[5];
                rndanswers[0] = answer1;
                rndanswers[1] = answer2;
                rndanswers[2] = answer3;
                rndanswers[3] = answer4;
                rndanswers[4] = answer5;

                int[] arrnumb = new int[5];
                for (int k = 0; k < arrnumb.length; k++) {
                    arrnumb[k] = -1;
                }

                int countansw = 0;
                a:
                while (countansw != 5) {
                    int numberansw = rn.nextInt(5);
                    for (int z = 0; z < arrnumb.length; z++) {
                        if (arrnumb[z] == numberansw) {
                            continue a;
                        }
                    }

                    arrnumb[countansw] = numberansw;
                    countansw++;
                }

                jLabel2.setText("<html><p style=\"width:530px\">" + quest + "</p></html>");
                jRadioButton1.setText(rndanswers[arrnumb[0]]);
                jRadioButton2.setText(rndanswers[arrnumb[1]]);
                jRadioButton3.setText(rndanswers[arrnumb[2]]);
                jRadioButton4.setText(rndanswers[arrnumb[3]]);
                jRadioButton5.setText(rndanswers[arrnumb[4]]);

                countqe++;

                dataUniversal[un1][0] = quest;
                dataUniversal[un2][0] = rndanswers[arrnumb[0]];
                dataUniversal[un3][0] = rndanswers[arrnumb[1]];
                dataUniversal[un4][0] = rndanswers[arrnumb[2]];
                dataUniversal[un5][0] = rndanswers[arrnumb[3]];
                dataUniversal[un6][0] = rndanswers[arrnumb[4]];

                if (dataUniversal[un2][0].equals(answer1)) {
                    dataUniversal[un2 - 1][1] = "$";
                }

                if (dataUniversal[un3][0].equals(answer1)) {
                    dataUniversal[un3 - 1][1] = "$";
                }

                if (dataUniversal[un4][0].equals(answer1)) {
                    dataUniversal[un4 - 1][1] = "$";
                }

                if (dataUniversal[un5][0].equals(answer1)) {
                    dataUniversal[un5 - 1][1] = "$";
                }

                if (dataUniversal[un6][0].equals(answer1)) {
                    dataUniversal[un6 - 1][1] = "$";
                }

                un1 += 6;
                un2 += 6;
                un3 += 6;
                un4 += 6;
                un5 += 6;
                un6 += 6;
            } 
            else {
                CountQuest++;
                Random rn = new Random();
                number = questioncheckEng[countqeEng];

                String quest = dataEng[questioncheckEng[countqeEng] * 6][0];

                String answer1 = dataEng[number * 6 + 1][0];
                String answer2 = dataEng[number * 6 + 2][0];
                String answer3 = dataEng[number * 6 + 3][0];
                String answer4 = dataEng[number * 6 + 4][0];
                String answer5 = dataEng[number * 6 + 5][0];

                String rndanswers[] = new String[5];
                rndanswers[0] = answer1;
                rndanswers[1] = answer2;
                rndanswers[2] = answer3;
                rndanswers[3] = answer4;
                rndanswers[4] = answer5;

                int[] arrnumb = new int[5];
                for (int k = 0; k < arrnumb.length; k++) {
                    arrnumb[k] = -1;
                }

                int countansw = 0;
                b:
                while (countansw != 5) {
                    int numberansw = rn.nextInt(5);
                    for (int z = 0; z < arrnumb.length; z++) {
                        if (arrnumb[z] == numberansw) {
                            continue b;
                        }
                    }

                    arrnumb[countansw] = numberansw;
                    countansw++;
                }

                jLabel2.setText("<html><p style=\"width:530px\">" + quest + "</p></html>");
                jRadioButton1.setText(rndanswers[arrnumb[0]]);
                jRadioButton2.setText(rndanswers[arrnumb[1]]);
                jRadioButton3.setText(rndanswers[arrnumb[2]]);
                jRadioButton4.setText(rndanswers[arrnumb[3]]);
                jRadioButton5.setText(rndanswers[arrnumb[4]]);

                countqeEng++;

                dataUniversal[un1][0] = quest;
                dataUniversal[un2][0] = rndanswers[arrnumb[0]];
                dataUniversal[un3][0] = rndanswers[arrnumb[1]];
                dataUniversal[un4][0] = rndanswers[arrnumb[2]];
                dataUniversal[un5][0] = rndanswers[arrnumb[3]];
                dataUniversal[un6][0] = rndanswers[arrnumb[4]];

                if (dataUniversal[un2][0].equals(answer1)) {
                    dataUniversal[un2 - 1][1] = "$";
                }

                if (dataUniversal[un3][0].equals(answer1)) {
                    dataUniversal[un3 - 1][1] = "$";
                }

                if (dataUniversal[un4][0].equals(answer1)) {
                    dataUniversal[un4 - 1][1] = "$";
                }

                if (dataUniversal[un5][0].equals(answer1)) {
                    dataUniversal[un5 - 1][1] = "$";
                }

                if (dataUniversal[un6][0].equals(answer1)) {
                    dataUniversal[un6 - 1][1] = "$";
                }

                un1 += 6;
                un2 += 6;
                un3 += 6;
                un4 += 6;
                un5 += 6;
                un6 += 6;
            }

        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception 328");
            e.printStackTrace();
        }
    }

    public void fillData() {
        try {
            jScrollPane2.setVisible(false);
            jButton5.setVisible(false);
            jLabel1.setVisible(false);
            jButton2.setVisible(false);

            File file = new File(path);
            FileReader fr = new FileReader(file);
            try (BufferedReader reader = new BufferedReader(fr)) {
                String lines = reader.readLine();
                data[0][0] = lines;

                while (lines != null) {
                    lines = reader.readLine();
                    data[index][0] = lines;
                    index++;
                }
            }

            int randomAnswersNumber = 0;
            Random rn = new Random();

            a:
            while (randomAnswersNumber != questioncheck.length) {
                int numberansw = rn.nextInt((data.length - 1) / 6);
                for (int z = 0; z < questioncheck.length; z++) {
                    if (questioncheck[z] == numberansw) {
                        continue a;
                    }
                }

                questioncheck[randomAnswersNumber] = numberansw;
                randomAnswersNumber++;
            }

            index = 1;
            File file2 = new File(pathEng);
            FileReader fr2 = new FileReader(file2);
            try (BufferedReader reader2 = new BufferedReader(fr2)) {
                String lines = reader2.readLine();
                dataEng[0][0] = lines;

                while (lines != null) {
                    lines = reader2.readLine();
                    dataEng[index][0] = lines;
                    index++;
                }
            }

            randomAnswersNumber = 0;
            Random rn2 = new Random();

            b:
            while (randomAnswersNumber != questioncheckEng.length) {
                int numberansw = rn2.nextInt((dataEng.length - 1) / 6);
                for (int z = 0; z < questioncheckEng.length; z++) {
                    if (questioncheckEng[z] == numberansw) {
                        continue b;
                    }
                }

                questioncheckEng[randomAnswersNumber] = numberansw;
                randomAnswersNumber++;
            }

            makeProgram();
        } 
        catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found exception");
        } 
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "IOException");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Design() {
        initComponents();
        fillData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(770, 435));
        setResizable(false);

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Відповісти");
        jButton1.setPreferredSize(new java.awt.Dimension(81, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setFont(new java.awt.Font("Roboto Light", 0, 16)); // NOI18N
        jRadioButton1.setText("Answer 1");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setFont(new java.awt.Font("Roboto Light", 0, 16)); // NOI18N
        jRadioButton2.setText("Answer 2");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jRadioButton3.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton3.setFont(new java.awt.Font("Roboto Light", 0, 16)); // NOI18N
        jRadioButton3.setText("Answer 3");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jRadioButton4.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton4.setFont(new java.awt.Font("Roboto Light", 0, 16)); // NOI18N
        jRadioButton4.setText("Answer 4");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto Light", 0, 16)); // NOI18N
        jLabel1.setText("Result");

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Вийти");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Завершити тест");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Roboto Light", 0, 16)); // NOI18N
        jLabel2.setText("QuestionsIsHere");
        jLabel2.setAutoscrolls(true);

        jLabel3.setFont(new java.awt.Font("Roboto Light", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Counter");

        jLabel4.setFont(new java.awt.Font("Roboto Light", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 51));
        jLabel4.setText("1:00");

        jButton5.setBackground(new java.awt.Color(0, 0, 0));
        jButton5.setFont(new java.awt.Font("Roboto Light", 0, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Перевірити відповіді");
        jButton5.setToolTipText("");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jRadioButton5.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton5.setFont(new java.awt.Font("Roboto Light", 0, 16)); // NOI18N
        jRadioButton5.setText("Answer 5");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        jTextPane1.setEditable(false);
        jTextPane1.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jScrollPane2.setViewportView(jTextPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(41, 41, 41))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton2)
                            .addComponent(jRadioButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 275, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton4)
                            .addComponent(jRadioButton5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 267, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(31, 31, 31)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton4)
                        .addGap(3, 3, 3)
                        .addComponent(jRadioButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jLabel4.getAccessibleContext().setAccessibleName("Timer");

        getAccessibleContext().setAccessibleName("MainForm");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jLabel4.setVisible(true);
        timer.stop();
        try {
            if (jRadioButton1.isSelected() || jRadioButton2.isSelected() || jRadioButton3.isSelected() || jRadioButton4.isSelected() || jRadioButton5.isSelected()) {
                if (jRadioButton1.isSelected()) {
                    String useranswer = jRadioButton1.getText();
                    if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 1][0])) {
                        if (dataUniversal[countActualQuestions * 6][1].equals("$")) {
                            CountRight++;
                        } 
                        else {
                            dataUniversal[countActualQuestions * 6][1] = "`";
                        }
                    } 
                    else if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 2][0])) {
                        if (dataUniversal[countActualQuestions * 6 + 1][1].equals("$")) {
                            CountRight++;
                        } 
                        else {
                            dataUniversal[countActualQuestions * 6 + 1][1] = "`";
                        }
                    } 
                    else if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 3][0])) {
                        if (dataUniversal[countActualQuestions * 6 + 2][1].equals("$")) {
                            CountRight++;
                        } 
                        else {
                            dataUniversal[countActualQuestions * 6 + 2][1] = "`";
                        }
                    } 
                    else if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 4][0])) {
                        if (dataUniversal[countActualQuestions * 6 + 3][1].equals("$")) {
                            CountRight++;
                        } 
                        else {
                            dataUniversal[countActualQuestions * 6 + 3][1] = "`";
                        }
                    } 
                    else if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 5][0])) {
                        if (dataUniversal[countActualQuestions * 6 + 4][1].equals("$")) {
                            CountRight++;
                        } 
                        else {
                            dataUniversal[countActualQuestions * 6 + 4][1] = "`";
                        }
                    }
                }

                if (jRadioButton2.isSelected()) {
                    String useranswer = jRadioButton2.getText();
                    if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 1][0])) {
                        if (dataUniversal[countActualQuestions * 6][1].equals("$")) {
                            CountRight++;
                        } 
                        else {
                            dataUniversal[countActualQuestions * 6][1] = "`";
                        }
                    } 
                    else if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 2][0])) {
                        if (dataUniversal[countActualQuestions * 6 + 1][1].equals("$")) {
                            CountRight++;
                        } 
                        else {
                            dataUniversal[countActualQuestions * 6 + 1][1] = "`";
                        }
                    } 
                    else if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 3][0])) {
                        if (dataUniversal[countActualQuestions * 6 + 2][1].equals("$")) {
                            CountRight++;
                        } 
                        else {
                            dataUniversal[countActualQuestions * 6 + 2][1] = "`";
                        }
                    } 
                    else if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 4][0])) {
                        if (dataUniversal[countActualQuestions * 6 + 3][1].equals("$")) {
                            CountRight++;
                        } 
                        else {
                            dataUniversal[countActualQuestions * 6 + 3][1] = "`";
                        }
                    } 
                    else if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 5][0])) {
                        if (dataUniversal[countActualQuestions * 6 + 4][1].equals("$")) {
                            CountRight++;
                        } 
                        else {
                            dataUniversal[countActualQuestions * 6 + 4][1] = "`";
                        }
                    }
                }

                if (jRadioButton3.isSelected()) {
                    String useranswer = jRadioButton3.getText();
                    if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 1][0])) {
                        if (dataUniversal[countActualQuestions * 6][1].equals("$")) {
                            CountRight++;
                        } 
                        else {
                            dataUniversal[countActualQuestions * 6][1] = "`";
                        }
                    } 
                    else if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 2][0])) {
                        if (dataUniversal[countActualQuestions * 6 + 1][1].equals("$")) {
                            CountRight++;
                        } 
                        else {
                            dataUniversal[countActualQuestions * 6 + 1][1] = "`";
                        }
                    } 
                    else if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 3][0])) {
                        if (dataUniversal[countActualQuestions * 6 + 2][1].equals("$")) {
                            CountRight++;
                        } else {
                            dataUniversal[countActualQuestions * 6 + 2][1] = "`";
                        }
                    } else if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 4][0])) {
                        if (dataUniversal[countActualQuestions * 6 + 3][1].equals("$")) {
                            CountRight++;
                        } else {
                            dataUniversal[countActualQuestions * 6 + 3][1] = "`";
                        }
                    } else if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 5][0])) {
                        if (dataUniversal[countActualQuestions * 6 + 4][1].equals("$")) {
                            CountRight++;
                        } else {
                            dataUniversal[countActualQuestions * 6 + 4][1] = "`";
                        }
                    }
                }

                if (jRadioButton4.isSelected()) {
                    String useranswer = jRadioButton4.getText();
                    if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 1][0])) {
                        if (dataUniversal[countActualQuestions * 6][1].equals("$")) {
                            CountRight++;
                        } else {
                            dataUniversal[countActualQuestions * 6][1] = "`";
                        }
                    } else if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 2][0])) {
                        if (dataUniversal[countActualQuestions * 6 + 1][1].equals("$")) {
                            CountRight++;
                        } else {
                            dataUniversal[countActualQuestions * 6 + 1][1] = "`";
                        }
                    } else if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 3][0])) {
                        if (dataUniversal[countActualQuestions * 6 + 2][1].equals("$")) {
                            CountRight++;
                        } else {
                            dataUniversal[countActualQuestions * 6 + 2][1] = "`";
                        }
                    } else if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 4][0])) {
                        if (dataUniversal[countActualQuestions * 6 + 3][1].equals("$")) {
                            CountRight++;
                        } else {
                            dataUniversal[countActualQuestions * 6 + 3][1] = "`";
                        }
                    } else if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 5][0])) {
                        if (dataUniversal[countActualQuestions * 6 + 4][1].equals("$")) {
                            CountRight++;
                        } else {
                            dataUniversal[countActualQuestions * 6 + 4][1] = "`";
                        }
                    }
                }

                if (jRadioButton5.isSelected()) {
                    String useranswer = jRadioButton5.getText();
                    if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 1][0])) {
                        if (dataUniversal[countActualQuestions * 6][1].equals("$")) {
                            CountRight++;
                        } else {
                            dataUniversal[countActualQuestions * 6][1] = "`";
                        }
                    } else if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 2][0])) {
                        if (dataUniversal[countActualQuestions * 6 + 1][1].equals("$")) {
                            CountRight++;
                        } else {
                            dataUniversal[countActualQuestions * 6 + 1][1] = "`";
                        }
                    } else if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 3][0])) {
                        if (dataUniversal[countActualQuestions * 6 + 2][1].equals("$")) {
                            CountRight++;
                        } else {
                            dataUniversal[countActualQuestions * 6 + 2][1] = "`";
                        }
                    } else if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 4][0])) {
                        if (dataUniversal[countActualQuestions * 6 + 3][1].equals("$")) {
                            CountRight++;
                        } else {
                            dataUniversal[countActualQuestions * 6 + 3][1] = "`";
                        }
                    } else if (useranswer.equals(dataUniversal[countActualQuestions * 6 + 5][0])) {
                        if (dataUniversal[countActualQuestions * 6 + 4][1].equals("$")) {
                            CountRight++;
                        } else {
                            dataUniversal[countActualQuestions * 6 + 4][1] = "`";
                        }
                    }
                }
            }

            countActualQuestions++;
            makeProgram();

            jRadioButton1.setSelected(false);
            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
            jRadioButton5.setSelected(false);
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception 790");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        if (jRadioButton1.isSelected()) {
            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
            jRadioButton5.setSelected(false);
        } else {
            jRadioButton2.setEnabled(true);
            jRadioButton3.setEnabled(true);
            jRadioButton4.setEnabled(true);
            jRadioButton5.setEnabled(true);
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        if (jRadioButton2.isSelected()) {
            jRadioButton1.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
            jRadioButton5.setSelected(false);
        } else {
            jRadioButton1.setEnabled(true);
            jRadioButton3.setEnabled(true);
            jRadioButton4.setEnabled(true);
            jRadioButton5.setEnabled(true);
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        if (jRadioButton3.isSelected()) {
            jRadioButton1.setSelected(false);
            jRadioButton2.setSelected(false);
            jRadioButton4.setSelected(false);
            jRadioButton5.setSelected(false);
        } else {
            jRadioButton1.setEnabled(true);
            jRadioButton2.setEnabled(true);
            jRadioButton4.setEnabled(true);
            jRadioButton5.setEnabled(true);
        }
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        if (jRadioButton4.isSelected()) {
            jRadioButton1.setSelected(false);
            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton5.setSelected(false);
        } else {
            jRadioButton1.setEnabled(true);
            jRadioButton2.setEnabled(true);
            jRadioButton3.setEnabled(true);
            jRadioButton5.setEnabled(true);
        }
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jLabel1.setText("Ви відповіли правильно на  " + CountRight + " питань з " + 200 + ".");
        timer.stop();
        jRadioButton1.setVisible(false);
        jRadioButton2.setVisible(false);
        jRadioButton3.setVisible(false);
        jRadioButton4.setVisible(false);
        jRadioButton5.setVisible(false);
        jButton1.setVisible(false);
        jButton3.setVisible(false);
        jLabel2.setVisible(false);
        jLabel1.setVisible(true);
        jButton2.setVisible(true);
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        jButton5.setVisible(true);

        this.setSize(480, 270);
        this.setLocationRelativeTo(null);
        setLayout(null);
        jLabel1.setSize(350, 35);
        jButton2.setSize(100, 35);
        jButton5.setSize(180, 35);
        jLabel1.setLocation(40, 20);
        jButton2.setLocation(330, 185);
        jButton5.setLocation(145, 185);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            if (countActualQuestions == 0) {
                JOptionPane.showMessageDialog(null, "Ви не дали відповіді хоча б на одне питання!");
                return;
            }

            for (int i = 0; i < dataUniversal.length - 2; i++) {
                if (dataUniversal[i][1].equals("$")) {
                    currentAnswers[counterCurrentAnswers] = i + 1;
                    counterCurrentAnswers++;
                }

                if (dataUniversal[i][1].equals("`")) {
                    wrongAnsNumber[counterWrongAnsNumber] = i + 1;
                    counterWrongAnsNumber++;
                }
            }

            quickSort(wrongAnsNumber, 0, wrongAnsNumber.length - 1);

            int countNumbers = 0;
            for (int k = 0; k < wrongAnsNumber.length; k++) {
                if (wrongAnsNumber[k] > 0) {
                    countNumbers++;
                }
            }

            int zzz = 0;
            int[] wrongAnswersNumber = new int[countNumbers];
            for (int k = 0; k < wrongAnsNumber.length; k++) {
                if (wrongAnsNumber[k] > 0) {
                    wrongAnswersNumber[zzz] = wrongAnsNumber[k];
                    zzz++;
                }
            }

            quickSort(currentAnswers, 0, currentAnswers.length - 1);

            int countCurrentAnswers = 0;
            for (int k = 0; k < currentAnswers.length; k++) {
                if (currentAnswers[k] > 0) {
                    countCurrentAnswers++;
                }
            }

            zzz = 0;
            int[] currentAnswersRows = new int[countCurrentAnswers];
            for (int k = 0; k < currentAnswers.length; k++) {
                if (currentAnswers[k] > 0) {
                    currentAnswersRows[zzz] = currentAnswers[k];
                    zzz++;
                }
            }

            jButton5.setVisible(false);
            jButton2.setVisible(false);
            jLabel1.setVisible(false);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = (int) screenSize.getWidth();
            int height = (int) screenSize.getHeight();
            if (width > 1915) {
                width = 1366;
                height = 768;
            }
            this.setSize(width, height);
            this.setLocationRelativeTo(null);
            setLayout(null);
            jScrollPane2.setVisible(true);
            jScrollPane2.setSize(width - 4, height - 32);
            jScrollPane2.setLocation(0, 0);

            String stroka="";
            countActualQuestions *= 6;
            for (int k = 0; k < dataUniversal.length - 2; k++) {
                if (k < countActualQuestions) {
                    stroka+=dataUniversal[k][0] + " " + dataUniversal[k][1] + "\n";

                    if ((k + 1) % 6 == 0) {
                        stroka+="\n";
                    }

                    if (k % 6 == 0) {
                        stroka+="\n";
                    }
                }
            }

            jTextPane1.setText(stroka);
            int[] dataLen = new int[dataUniversal.length - 2];
            for (int k = 0; k < dataUniversal.length - 2; k++) {
                dataLen[k] = dataUniversal[k][0].length();
            }

            MutableAttributeSet attrs = jTextPane1.getInputAttributes();
            StyledDocument doc = jTextPane1.getStyledDocument();

            int y = 0;
            int y2 = 0;
            int check = 0;
            int checkgreen = 0;
            for (int k = 0; k < jTextPane1.getText().length(); k++) {
                String symbol = jTextPane1.getText(k, 1);
                StyleConstants.setForeground(attrs, Color.black);

                if (symbol.equals("$")) {
                    jTextPane1.getDocument().remove(k, 1);
                    StyleConstants.setForeground(attrs, Color.green);
                    y = 0;
                    y = currentAnswersRows[checkgreen];

                    if (y != 0) {
                        checkgreen++;
                        doc.setCharacterAttributes((k + 1), dataLen[y] + 2, attrs, false);
                    } else {
                        return;
                    }
                }

                if (symbol.equals("`")) {
                    jTextPane1.getDocument().remove(k, 1);
                    StyleConstants.setForeground(attrs, Color.red);
                    y2 = wrongAnswersNumber[check];

                    if (y2 != 0) {
                        check++;
                        doc.setCharacterAttributes((k + 1), dataLen[y2] + 2, attrs, false);
                    }
                }

                if (y2 > data.length) {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Exception");
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        if (jRadioButton5.isSelected()) {
            jRadioButton1.setSelected(false);
            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
        } else {
            jRadioButton1.setEnabled(true);
            jRadioButton2.setEnabled(true);
            jRadioButton3.setEnabled(true);
            jRadioButton4.setEnabled(true);
        }
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    public static void quickSort(int[] arr, int start, int end) {
        int partition = partition(arr, start, end);

        if (partition - 1 > start) {
            quickSort(arr, start, partition - 1);
        }

        if (partition + 1 < end) {
            quickSort(arr, partition + 1, end);
        }
    }

    public static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];

        for (int i = start; i < end; i++) {
            if (arr[i] < pivot) {
                int temp = arr[start];
                arr[start] = arr[i];
                arr[i] = temp;
                start++;
            }
        }

        int temp = arr[start];
        arr[start] = pivot;
        arr[end] = temp;

        return start;
    }

    class TimerTick implements ActionListener {

        int countdown = 60;

        @Override
        public void actionPerformed(ActionEvent e) {
            jLabel4.setVisible(true);
            countdown--;

            if (countdown == 60) {
                jLabel4.setText(String.valueOf("1:00"));
            }

            if (countdown > 9 && countdown < 60) {
                jLabel4.setText(String.valueOf("0:" + countdown));
            }

            if (countdown < 10) {
                jLabel4.setText(String.valueOf("0:0" + countdown));
            }

            if (countdown == 0) {
                jLabel4.setVisible(true);
                timer.stop();
                jButton1.doClick();
                jRadioButton1.setSelected(false);
                jRadioButton2.setSelected(false);
                jRadioButton3.setSelected(false);
                jRadioButton4.setSelected(false);
                jRadioButton5.setSelected(false);
                jRadioButton1.setEnabled(true);
                jRadioButton2.setEnabled(true);
                jRadioButton3.setEnabled(true);
                jRadioButton4.setEnabled(true);
                jRadioButton5.setEnabled(true);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Design.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Design.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Design.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Design.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Design().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}