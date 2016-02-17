/**
 * Created by COOLib on 15.03.2015.
 */
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class RemakingText {

    private JPanel panel;
    private JFrame frame;
    private JTextArea ta;

    String[] russ = {"если","спис","чтобы","еще","потому что","может быть","возможно","конечно","комнат","заранее","никогда","однажды","нач","слаб","бесплатн","вверх",
            "очень","громк","вместе","пока","вниз","иначе","вероятно","медленн","наконец","теперь","кроме","юг","север","потом","старани","противник",
            "тот","только","как"};

    String[] oldruss = {"ежели","свит","дабы","аще","ибо","авось","авось","вестимо","горниц","загодя","николи","однова","поч","бренн","безмездн","ввыспрь",
            "вельми","велегласн","вкупе","доколь","додолу","онако","имоверно","ленн","напоследи","ныне","опричь","полудень","полуноч","посем","радени","супостат",
            "той","токмо","ако"};

    public static void main(String[] args) {
        RemakingText text = new RemakingText();
        text.go();
    }

    private void go(){
        frame = new JFrame("Remaking text");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel back = new JPanel(layout);
        back.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        back.setBackground(Color.DARK_GRAY);

        Box buttonBox = new Box(BoxLayout.X_AXIS);

        JButton start = new JButton("Open");
        start.addActionListener(new MyStartListener());
        buttonBox.add(start);

        JButton going = new JButton("Go");
        going.addActionListener(new MyGoListener());
        buttonBox.add(going);

        JButton save = new JButton("Save");
        save.addActionListener(new MySaveListener());
        buttonBox.add(save);

        panel = new JPanel();
        back.add(panel);

        ta = new JTextArea();
        JScrollPane pane = new JScrollPane(ta);
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        back.add(BorderLayout.CENTER,pane);
        back.add(BorderLayout.SOUTH,buttonBox);

        frame.getContentPane().add(back);
        frame.setBounds(600,300,650,650);
        frame.setVisible(true);
    }

    public class MyGoListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try {
                String str = ta.getText();
                String st = str.replace(russ[0], oldruss[0]);
                    for (int j = 1; j < russ.length; j++) {
                        st = st.replace(russ[j], oldruss[j]);
                    }
                ta.setText("");
                ta.append(st);

            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public class MyStartListener implements ActionListener{
        public void actionPerformed(ActionEvent e){

            try {
                JFileChooser dialog = new JFileChooser();
                dialog.setFileFilter(new FileNameExtensionFilter("Текстовые файлы", "txt"));
                dialog.showOpenDialog(frame);
                File file = dialog.getSelectedFile();

                FileInputStream inF = new FileInputStream(file);
                BufferedReader an = new BufferedReader(new InputStreamReader(inF, "Cp1251"));
                ta.setText("");
                String str;

                for (int i=0;i<file.length();i++) {
                     str = an.readLine();
                    if(str!=null){
                        ta.append(str + "\n");
                    }
                    else {
                        break;
                    }
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
            }

        }
    }

    public class MySaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            try {
                JFileChooser dialog = new JFileChooser();
                dialog.showSaveDialog(frame);
                File file = dialog.getSelectedFile();
                dialog.setVisible(true);
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(ta.getText());
                writer.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
