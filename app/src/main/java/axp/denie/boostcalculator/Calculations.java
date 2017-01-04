package axp.denie.boostcalculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 3.2.2016 г..
 */
public class Calculations {

        public double Boost_calculator(double power, double boost){
           double res;
            res = (boost + 14.696) * power / 14.696;
            return res;
        }


        public double[] Transmission_loss(double hp){
            double fwd = hp*0.11;
            double rwd = hp*0.15;
            double awd = hp*0.23;
            double[] array = {fwd, rwd, awd};
            return array;
        }

        public double Altitude_loss(double elevation, double hp){
            double Loss = (elevation * 0.03 * hp) / 1000;
            return Loss;
        }

        public double Power_liter(double hp, double liter){
            double temp = hp / liter;
            return temp;
        }
// calculations for fragments:

        public double to_bar(double psi){
            double bar;
            bar = psi * 0.0689475729;
            return bar;
        }

         public double to_psi (double bar){
             double psi;
             psi = bar * 14.5037738;
             return psi;
         }


    }


