
package pkg2dofkinematics;
/**
 *
 * @author sakib
 */
public class pkg2dofkinematics {

    public static void main(String[] args) {
        //Forward kinematics 
        double l1 = 100, l2 = 87, q11 = 0, q22 = 0;  //(int) Math.toDegrees(10)
//        double q1 = (double) (Math.toRadians(q11));
//        double q2 = (double) (Math.toRadians(q22));
//        double x = (double) ((l1 * Math.cos(q1)) + ((l2) * Math.cos(q1 + q2)));
//        double y = (double) ((l1 * Math.sin(q1)) + ((l2) * Math.sin(q1 + q2)));

        double q1 = (double) (Math.toRadians(q11));
        double q2 = (double) (Math.toRadians(q22));
        
        double x = (double) (l1 * Math.cos(q1));
        x = x + l2;
        x = x * Math.cos(q1 + q2);

        double y = (double) (l1 * Math.sin(q1));
        y = y + l2;
        y = y * Math.sin(q1 + q2);

        System.out.println("Forware Kinematics x= " + x + " y= " + y);

        //Inverse kinematics
        // double n = (double) ((Math.pow(x, 2)) + (Math.pow(y, 2)) - (Math.pow(l1, 2)) - (Math.pow(l2, 2))) / (2 * (l1 * l2));
        //int q3 = (int) (float) Math.toDegrees(Math.tan(n));
        //int alpha = (int) (float) Math.toDegrees(Math.atan(y / x));
        //System.out.println(alpha);
        double Q2;
        double Q1;

        if (q2 >= 0) {
            Q2 = (int) Math.acos((x * x) + (y * y) - (l1 * l1) - (l2 * l2)) / (2 * l1 * l2);
            // Q2=Q2*Math.PI/180;
            double beta = (double) ((double) Math.atan(l2 * (Math.sin(Q2))) / l1 + l2 * Math.cos(Q2));
            double alpha = (double) Math.atan(y / x);
            System.out.println("beta= " + beta);
            System.out.println("alpha= " + alpha);
            Q1 = alpha - beta;
            // Q1=Q1*Math.PI/180;

        } else {

            Q2 = -(int) Math.acos((x * x) + (y * y) - (l1 * l1) - (l2 * l2)) / (2 * l1 * l2);
            // Q2=Q2*Math.PI/180;
            double beta = (double) ((double) (Math.atan(l2 * (Math.sin(Q2)))) / l1 + l2 * Math.cos(Q2));
            double alpha = (double) Math.atan(y / x);
            System.out.println("beta= " + beta);
            System.out.println("alpha= " + alpha);
            Q1 = alpha + beta;
            // Q1=Q1*Math.PI/180;
        }

        System.out.println("Q2= " + Q2);
        System.out.println("Q1= " + Q1);

    }

}
