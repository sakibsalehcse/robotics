package pkg2dof;
import coppelia.IntW;
import coppelia.remoteApi;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Myvrep2dof {

    remoteApi vrep;

    public Myvrep2dof() {

        JFrame m = new JFrame("2 DOF");
        m.setSize(500, 500);
        m.setLocationRelativeTo(null);
        m.setVisible(true);
        m.setDefaultCloseOperation(m.EXIT_ON_CLOSE);
        m.setLayout(null);

        JTextField t = new JTextField("x1");
        t.setBackground(Color.white);
        t.setBounds(100, 30, 60, 30);

        JTextField t2 = new JTextField("x2");
        t2.setBackground(Color.white);
        t2.setBounds(170, 30, 60, 30);

        JTextField t3 = new JTextField("r1");
        t3.setBackground(Color.white);
        t3.setBounds(240, 30, 60, 30);

        JTextField t4 = new JTextField("r2");
        t4.setBackground(Color.white);
        t4.setBounds(310, 30, 60, 30);

        JTextField t5 = new JTextField("Value of X:");
        t5.setBackground(Color.white);
        t5.setBounds(150, 150, 80, 30);

        JTextField t6 = new JTextField("Value of Y:");
        t6.setBackground(Color.white);
        t6.setBounds(240, 150, 80, 30);
        
        JTextField t7 = new JTextField("value of j1");
        t7.setBackground(Color.white);
        t7.setBounds(150, 200, 80, 30);

        JTextField t8 = new JTextField("value of j2");
        t8.setBackground(Color.white);
        t8.setBounds(240, 200, 80, 30);
        
        JButton b = new JButton("Calculate");
        b.setBounds(180, 80, 100, 30);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int Q1 = Integer.parseInt(t.getText());
                int Q2 = Integer.parseInt(t2.getText());
                int r1 = Integer.parseInt(t3.getText());
                int r2 = Integer.parseInt(t4.getText());
                int c = Q1 + Q2 + r1 + r2;
                String c2 = new Integer(c).toString();
                t5.setText(c2);

            }
        });
        
        JLabel jl = new JLabel("");
        jl.setBounds(160, 240, 250, 30);

        JButton b2 = new JButton("Start simulation");
        b2.setBounds(160, 280, 150, 30);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jl.setText("Enter value of j1,j2 and submit");
                b2.setText("submit");
                int clientID;
                try {
                    vrep = new remoteApi();
                    vrep.simxFinish(-1);

                    clientID = vrep.simxStart("127.0.0.1", 19997, true, true, 5000, 5);
                    if (clientID == -1) {
                        System.out.println("Cant connect");
                    } else {
                        vrep.simxStartSimulation(clientID, vrep.simx_opmode_blocking);
                    }
                } catch (Exception ex) {
                    System.out.println("Error=" + ex.getMessage());
                    return;
                }

                IntW handle = new IntW(0);

                int code = vrep.simxGetObjectHandle(clientID, "j1", handle, vrep.simx_opmode_blocking);
                System.out.println("Handle found");
                
                Double d = Double.parseDouble(t7.getText());
                System.out.println("now setting value");
                float deg = (float) (d * Math.PI / 180);
                //System.out.println("rad=" + deg);
                code = vrep.simxSetJointTargetPosition(clientID, handle.getValue(), deg, vrep.simx_opmode_streaming);
                System.out.println("Joint set complete");

                IntW handle2 = new IntW(0);

                int code2 = vrep.simxGetObjectHandle(clientID, "j2", handle2, vrep.simx_opmode_blocking);
                System.out.println("Handle found");

                Double d2 = Double.parseDouble(t8.getText());
                System.out.println("now setting value");
                float deg2 = (float) (d2 * Math.PI / 180);
                //System.out.println("rad=" + deg);
                code2 = vrep.simxSetJointTargetPosition(clientID, handle2.getValue(), deg2, vrep.simx_opmode_streaming);
                System.out.println("Joint set complete");
             

            }
        });

        JButton b3 = new JButton("Stop simulation");
        b3.setBounds(160, 330, 150, 30);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int clientID;
                try {
                    vrep = new remoteApi();
                    vrep.simxFinish(-1);

                    clientID = vrep.simxStart("127.0.0.1", 19997, true, true, 5000, 5);
                    if (clientID == -1) {
                        System.out.println("Cant connect");
                    } else {
                        vrep.simxStartSimulation(clientID, vrep.simx_opmode_blocking);
                    }
                } catch (Exception ex) {
                    System.out.println("Error=" + ex.getMessage());
                    return;
                }

                vrep.simxStopSimulation(clientID, vrep.simx_opmode_blocking);
                b2.setText("Start Simulation");
                jl.setText("");
            }

        });

        m.add(t);
        m.add(t2);
        m.add(t3);
        m.add(t4);
        m.add(t7);
        m.add(t8);
        m.add(b);
        m.add(t5);
        m.add(t6);
        m.add(b2);
        m.add(b3);
        m.add(jl);
        m.repaint();
        m.validate();

    }

    private void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception ex) {

        }
    }

    public static void main(String[] args) {

        new Myvrep2dof();

    }

}
