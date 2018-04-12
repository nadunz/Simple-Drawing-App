import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class DrawingApp extends JFrame {


    private JTextField blue_txt;
    private JPanel canvas;
    private JButton draw_btn;
    private JTextField green_txt;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JPanel jPanel1;
    private JLabel notification_lbl;
    private JTextField radius_txt;
    private JTextField red_txt;
    private JTextField x_txt;
    private JTextField y_txt;
    
    public DrawingApp() {

        setupGUI();
        // set gui to the middle of the screen
        setLocationRelativeTo(null);

        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                x_txt.setText(evt.getX()+"");
                y_txt.setText(evt.getY()+"");
                buttonAction();
            }
        });

        canvas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                x_txt.setText(evt.getX()+"");
                y_txt.setText(evt.getY()+"");
                buttonAction();
            }
        });

        draw_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonAction();
            }
        });

    }


    public void drawCircle(Point location,Color color,int radius){
        Graphics g= canvas.getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.fillOval(location.x, location.y, radius, radius);
        g2d.dispose();
    }
    
    public void notifyError(String msg){
        notification_lbl.setForeground(Color.red);
        notification_lbl.setText(msg);
    }
    
    public void buttonAction(){

        String x_text = x_txt.getText();
        String y_text = y_txt.getText();
        String radius_text = radius_txt.getText();
        String red_text = red_txt.getText();
        String green_text = green_txt.getText();
        String blue_text = blue_txt.getText();
        
        if(x_text.isEmpty()){
            notifyError("Empty x location");
        }else if(y_text.isEmpty()){
            notifyError("Empty y location");
        }else if(radius_text.isEmpty()){
            notifyError("Empty radius");
        }else if(red_text.isEmpty()){
            notifyError("Empty red component");
        }else if(green_text.isEmpty()){
            notifyError("Empty green component");
        }else if(blue_text.isEmpty()){
            notifyError("Empty blue component");
        }else{
            int x = 0,y = 0,radius = 0,red = 0,green = 0,blue = 0;
            boolean error = false; 
            try {
                x = Integer.parseInt(x_text);
            } catch (NumberFormatException e) {
                error = true;
                notifyError("Bad value for location x");
            }

            try {
                y = Integer.parseInt(y_text);
            } catch (NumberFormatException e) {
                error = true;
                notifyError("Bad value for location y");
            }
            try {
                radius = Integer.parseInt(radius_text);
            } catch (NumberFormatException e) {
                error = true;
                notifyError("Bad value for radius");
            }

            try {
                red = Integer.parseInt(red_text);
            } catch (NumberFormatException e) {
                error = true;
                notifyError("Bad value for red component");
            }
            try {
                green = Integer.parseInt(green_text);
            } catch (NumberFormatException e) {
                error = true;
                notifyError("Bad value for green component");
            }

            try {
                blue = Integer.parseInt(blue_text);
            } catch (NumberFormatException e) {
                error = true;
                notifyError("Bad value for blue component");
            }
            
            if(red < 0 || red > 255){
                notifyError("Red must be in 0 to 255 range");
            }else if(green < 0 || green > 255){
                notifyError("Green must be in 0 to 255 range");
            }else if(blue < 0 || blue > 255){
                notifyError("Blue must be in 0 to 255 range");
            }else if(radius <= 0){
                notifyError("Radius must be greater than 0");
            }else{
                Point location = new Point(x-radius/2, y-radius/2);
                Color color = new Color(red, green, blue);
                if(!error){
                    notification_lbl.setForeground(new Color(51, 153, 0));
                    notification_lbl.setText("No Errors");
                    drawCircle(location ,color, radius);
                }
            }
        }
    }
    
    private void setupGUI() {

        jPanel1 = new JPanel();
        canvas = new JPanel();
        notification_lbl = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        radius_txt = new JTextField();
        x_txt = new JTextField();
        y_txt = new JTextField();
        red_txt = new JTextField();
        green_txt = new JTextField();
        blue_txt = new JTextField();
        draw_btn = new JButton();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simple Drawing Tool");

        canvas.setBackground(new Color(255, 255, 255));

        GroupLayout canvasLayout = new GroupLayout(canvas);
        canvas.setLayout(canvasLayout);
        canvasLayout.setHorizontalGroup(
            canvasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        canvasLayout.setVerticalGroup(
            canvasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 333, Short.MAX_VALUE)
        );

        notification_lbl.setBackground(new Color(204, 204, 204));
        notification_lbl.setFont(new Font("Tahoma", 1, 14)); 
        notification_lbl.setForeground(new Color(51, 153, 0));
        notification_lbl.setHorizontalAlignment(SwingConstants.CENTER);
        notification_lbl.setText("No Errors");
        notification_lbl.setOpaque(true);

        jLabel2.setText("Location:");
        jLabel3.setText("Radius: ");
        jLabel4.setText("Color:");

        radius_txt.setHorizontalAlignment(JTextField.CENTER);
        radius_txt.setText("0");

        x_txt.setHorizontalAlignment(JTextField.CENTER);
        x_txt.setText("0");

        y_txt.setHorizontalAlignment(JTextField.CENTER);
        y_txt.setText("0");

        red_txt.setHorizontalAlignment(JTextField.CENTER);
        red_txt.setText("0");

        green_txt.setHorizontalAlignment(JTextField.CENTER);
        green_txt.setText("0");

        blue_txt.setHorizontalAlignment(JTextField.CENTER);
        blue_txt.setText("0");

        draw_btn.setBackground(new Color(255, 51, 204));
        draw_btn.setText("Draw  It");
        draw_btn.setFocusPainted(false);

        jLabel5.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new Color(204, 0, 0));
        jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel5.setText("R");

        jLabel6.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new Color(0, 102, 0));
        jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel6.setText("G");

        jLabel7.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new Color(0, 0, 153));
        jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel7.setText("B");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(canvas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(notification_lbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(x_txt, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(y_txt, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jLabel3)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radius_txt, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(red_txt, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(green_txt, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(blue_txt, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(draw_btn)
                .addGap(64, 64, 64))
        );

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(canvas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(radius_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(x_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(y_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(red_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(green_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(blue_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(draw_btn))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(notification_lbl, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

   
  
    public static void main(String args[]) {
       
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    
        new DrawingApp().setVisible(true);
       
    }

}
