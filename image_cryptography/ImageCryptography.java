import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;  
import javax.crypto.CipherInputStream;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

class miniProject extends JFrame implements ActionListener{
    JButton b1 = new JButton("Encrypt");
    JButton b2 = new JButton("Decrypt");//input text and buttons
    JButton b3 = new JButton("Choose file");
    JLabel l1 = new JLabel("Encryption Key:");
    JPasswordField t1 = new JPasswordField(20);
    JLabel l2 = new JLabel("Decryption Key:");
    JPasswordField t2 = new JPasswordField(20);
    Cipher cipher;
    Key key;

    miniProject() throws NoSuchAlgorithmException, NoSuchPaddingException{
        cipher=Cipher.getInstance("AES");
        setTitle("Image Crytography");
        b1.setBounds(30, 40, 85, 30);
        add(b1);
        l1.setBounds(150, 15, 200, 30);
        add(l1);
        t1.setBounds(150, 40, 200, 30);
        add(t1);
        b2.setBounds(30, 100, 85, 30);
        add(b2);
        t2.setBounds(150, 100, 200, 30);
        add(t2);
        l2.setBounds(150, 75, 200, 30);
        add(l2);
        add(b3);
        setVisible(true);
        setSize(400, 210);
        setLayout(null);
        b1.addActionListener(this);
        b2.addActionListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void encrypt(File file, Key key) throws InvalidKeyException, FileNotFoundException, IOException{
        cipher.init(Cipher.ENCRYPT_MODE, key); 
        CipherInputStream cipt=new CipherInputStream(new FileInputStream(file), cipher);
        FileOutputStream fileip=new FileOutputStream(new File("C:\\Users\\AISH GOYAL\\Desktop\\image_cryptography\\images\\Encrypted.jpg"));
        int i;
        while((i=cipt.read())!=-1){
            fileip.write(i);
        }
        JOptionPane.showMessageDialog(this, "File Encrypted Successfully!");
    }

    public void decrypt(File file, Key key) throws InvalidKeyException, FileNotFoundException, IOException{
        cipher.init(Cipher.DECRYPT_MODE, key);
        CipherInputStream ciptt=new CipherInputStream(new FileInputStream(file), cipher);
        FileOutputStream fileop=new FileOutputStream(new File("C:\\Users\\AISH GOYAL\\Desktop\\image_cryptography\\images\\Decrypted.jpg"));
        int j;
        while((j=ciptt.read())!=-1)
        {
            fileop.write(j);
        }
        JOptionPane.showMessageDialog(this, "File Decrypted Successfully!");
    }
    public void actionPerformed(ActionEvent e){
        String s1=t1.getText(), s2=t2.getText();
        try{
            if(e.getSource()==b1){
                if(t1.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this, "Enter Key!");
                }
                else{
                    key = new SecretKeySpec(t1.getText().getBytes(), "AES");
                    s1 = t1.getText();
                    JFileChooser fc=new JFileChooser();
                    fc.showOpenDialog(null);
                    File file = fc.getSelectedFile();
                    encrypt(file, key);
                }
            }
            if(e.getSource()==b2){
                if(t2.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this, "Enter Key!");
                }
                else{
                    s2 = t2.getText();
                    if(s2.equals(s1)){
                        JFileChooser fc2 = new JFileChooser();
                        fc2.showOpenDialog(null);
                        File file2 = fc2.getSelectedFile();
                        decrypt(file2, key);
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Incorrect Key!");
                    }
                }
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
public class ImageCryptography{
    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException{
        miniProject m = new miniProject();
    }
}