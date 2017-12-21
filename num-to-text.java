//**********************************************************
//		    PROJECT – Number to Word Converter
//**********************************************************

import java.awt.Container;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.LayoutStyle;

class word extends JFrame
{
    private JButton cls;
    private JTextField input;
    private JButton jButton1;
    private JLabel jLabel1;
    private JLabel result1;
    private JLabel result2;
    private JLabel result3;

    public word()
    {
        initComponents();
        setResizable(false);
        setTitle("Number to Word converter - By Dipjyoti Bisharad");
        this.jButton1.setToolTipText("Convert into Words");
        this.cls.setToolTipText("Clear");
        this.input.requestFocus();
    }

    private void initComponents()
    {
        this.input = new JTextField();
        this.jLabel1 = new JLabel();
        this.jButton1 = new JButton();
        this.cls = new JButton();
        this.result1 = new JLabel();
        this.result2 = new JLabel();
        this.result3 = new JLabel();
        setDefaultCloseOperation(3);
        setLocationByPlatform(true);

        this.jLabel1.setText("Enter an Integer");

        this.jButton1.setText("Convert");
        this.jButton1.setCursor(new Cursor(12));
        this.jButton1.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    word.this.jButton1ActionPerformed(evt);
                }
            });
        this.cls.setText("C");
        this.cls.setCursor(new Cursor(12));
        this.cls.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    word.this.clsActionPerformed(evt);
                }
            });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25).addComponent(this.jLabel1)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(this.input, -2, 151, -2)
                        .addGap(18, 18, 18)
                        .addComponent(this.jButton1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(this.cls))
                    .addComponent(this.result1, -1, 488, 32767)
                    .addComponent(this.result2, -1, -1, 32767)
                    .addComponent(this.result3, -1, -1, 32767))
                    .addContainerGap(-1, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        .addComponent(this.input, -2, -1, -2)
        .addComponent(this.jLabel1)
        .addComponent(this.jButton1)
        .addComponent(this.cls))
        .addGap(18, 18, 18)
        .addComponent(this.result1, -2, 13, -2)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(this.result2, -2, 13, -2)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(this.result3, -2, 13, -2)
        .addContainerGap()));
        pack();
    }

    private void jButton1ActionPerformed(ActionEvent evt)
    {
        long n = 0L;long p = 0L;
        String fs = "";String a = "";String s = "";
        String ph = this.input.getText();
        ph = ph.trim();
        try
        {
            a = Long.toString(Math.abs(Long.parseLong(ph)));
            if (Long.parseLong(a) != 0L)
            {
                if (a.length() <= 7)
                {
                    n = Long.parseLong(a);
                    s = calculate(n);
                }
                else if (a.length() <= 14)
                {
                    p = Long.parseLong(a.substring(a.length() - 7, a.length()));
                    n = Long.parseLong(a.substring(0, a.length() - 7));
                    if (acheck(calculate(p))) {
                        s = calculate(n) + "crore and " + calculate(p);
                    } else {
                        s = calculate(n) + "crore " + calculate(p);
                    }
                }
                else
                {
                    p = Long.parseLong(a.substring(a.length() - 7, a.length()));
                    n = Long.parseLong(a.substring(a.length() - 14, a.length() - 7));
                    String fd = calculate(n) + "crore ";
                    long k = Long.parseLong(a.substring(0, a.length() - 14));
                    if (!acheck(calculate(p))) {
                        s = calculate(k) + "crore " + fd + calculate(p);
                    } else {
                        s = calculate(k) + "crore " + fd + "and " + calculate(p);
                    }
                }
                this.result1.setText("");
                this.result3.setText("");
                display(s, Long.parseLong(ph));
            }
            else
            {
                this.result1.setText("");
                this.result3.setText("");
                this.result2.setText("Zero.");
            }
            if ((!this.result1.getText().equals("")) && (this.result2.getText().trim().equals("")))
            {
                String z = this.result1.getText();
                this.result2.setText(z.replace('-', ' '));
                this.result1.setText("");
            }
        }
        catch (Exception e)
        {
            this.result1.setText("");
            this.result3.setText("");
            if (ph.equals("")) {
                this.result2.setText("No input !!!!");
            } else if (spccheck(ph)) {
                this.result2.setText("Invalid input !!!!!");
            } else if (checksp(ph)) {
                this.result2.setText("The number cannot have spaces in between !!!!");
            } else if (checkd(ph)) {
                this.result2.setText("Decimal numbers are not allowed !!!!!");
            } else {
                this.result2.setText("Number is out of range !!!!!");
            }
        }
    }

    private void clsActionPerformed(ActionEvent evt)
    {
        this.input.setText("");
        this.result1.setText("");
        this.result2.setText("");
        this.result3.setText("");
        this.input.requestFocus();
    }

    public String cap(String x)
    {
        String s = "";
        s = Character.toUpperCase(x.charAt(0)) + x.substring(1);
        return s;
    }

    public String calculate(long n)
    {
        String[] o = { "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
        String[] j = { "", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "ninteen" };
        String[] t = { "", "ten", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety" };
        int i = 0;
        long g = n;
        String s = "";String fs = "";
        fs = Long.toString(g);
        StringBuffer ns = new StringBuffer(fs);
        do
        {
            i++;
            int a = (int)(n % 10L);
            if ((i == 3) && (!s.trim().equals(""))) {
                s = "and " + s;
            }
            if ((a == 0) && (i != 4) && (i != 6))
            {
                n /= 10L;
            }
            else
            {
                if ((i == 4) && (ns.length() >= 5)) {
                    if ((ns.reverse().charAt(4) == '0') && (a == 0))
                    {
                        n /= 10L;
                        continue;
                    }
                }
                if (i == 3)
                {
                    s = o[a] + " hundred " + s;
                    n /= 10L;
                }
                else
                {
                    int b = (int)(n / 10L);
                    if ((i == 1) || (i == 4) || (i == 8) || (i == 6))
                    {
                        if (b % 10 != 1)
                        {
                            if (i == 4)
                            {
                                s = o[a] + " thousand " + s;
                                n /= 10L;
                            }
                            else if (i == 6)
                            {
                                s = o[a] + " lakh " + s;
                                n /= 10L;
                            }
                            else
                            {
                                s = o[a] + " " + s;
                                n /= 10L;
                            }
                        }
                        else if (i == 4)
                        {
                            s = j[a] + " thousand " + s;
                            n /= 10L;
                        }
                        else if (i == 6)
                        {
                            s = j[a] + " lakh " + s;
                            n /= 10L;
                        }
                        else
                        {
                            s = j[a] + " " + s;
                            n /= 10L;
                        }
                    }
                    else if (a == 1)
                    {
                        if (fs.charAt(fs.length() - i + 1) == '0')
                        {
                            s = t[a] + " " + s;
                            n /= 10L;
                        }
                        else
                        {
                            n /= 10L;
                        }
                    }
                    else if (a != 1)
                    {
                        s = t[a] + " " + s;
                        n /= 10L;
                    }
                }
            }
        } while (n != 0L);
        s = s.replace("  ", " ");
        return s;
    }

    private void inputActionPerformed(ActionEvent evt) {}

    public boolean checkd(String b)
    {
        boolean f = false;
        for (int i = 0; i < b.length(); i++)
        {
            char c = b.charAt(i);
            if (c == '.')
            {
                f = true;
                break;
            }
        }
        return f;
    }

    public void display(String m, long n)
    {
        m = m.trim() + ".";
        if (n < 0L) {
            m = "minus " + m;
        }
        m = cap(m);
        if (m.length() <= 70)
        {
            this.result2.setText(m);
        }
        else if (m.length() <= 140)
        {
            String a = m.substring(0, 70);
            int k = a.lastIndexOf(' ');
            this.result1.setText(m.substring(0, k + 1) + " -");
            this.result2.setText(m.substring(k + 1, m.length()));
        }
        else
        {
            String a = m.substring(0, 70);
            int k = a.lastIndexOf(' ');
            this.result1.setText(m.substring(0, k + 1) + "-");
            String b = m.substring(k + 1, 140);
            int j = b.lastIndexOf(' ');
            this.result2.setText(m.substring(k + 1, k + 2 + j) + "-");
            this.result3.setText(m.substring(k + 2 + j, m.length()));
        }
    }

    public boolean checksp(String b)
    {
        boolean p = false;
        for (int i = 0; i < b.length(); i++)
        {
            char c = b.charAt(i);
            if (c == ' ')
            {
                p = true;
                break;
            }
        }
        return p;
    }

    public boolean acheck(String k)
    {
        k = k.trim();
        boolean p = false;
        int m = 0;
        for (int i = 0; i < k.length(); i++)
        {
            char c = k.charAt(i);
            if (c == ' ') {
                m++;
            }
        }
        if ((m <= 1) && (!k.equals(""))) {
            p = true;
        }
        return p;
    }

    public boolean spccheck(String k)
    {
        k = k.trim();
        boolean p = false;
        int m = 0;
        for (int i = 0; i < k.length(); i++)
        {
            char c = k.charAt(i);
            if ((!Character.isDigit(c)) && (!Character.isWhitespace(c)))
            {
                if (c == '.') {
                    m++;
                }
                if (m == 1) {
                    m = 2;
                } else {
                    p = true;
                }
            }
        }
        return p;
    }

    public boolean checkl(String b)
    {
        boolean p = false;
        for (int i = 0; i < b.length(); i++)
        {
            char c = b.charAt(i);
            if (Character.isLetter(c))
            {
                p = true;
                break;
            }
        }
        return p;
    }

    public static void main(String[] args)
    {
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(word.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            Logger.getLogger(word.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            Logger.getLogger(word.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (UnsupportedLookAndFeelException ex)
        {
            Logger.getLogger(word.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable()
            {
                public void run()
                {
                    new word().setVisible(true);
                }
            });    }}
