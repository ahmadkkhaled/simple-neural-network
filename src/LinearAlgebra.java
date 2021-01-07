import java.util.ArrayList;
import java.util.List;

public class LinearAlgebra {
    public static double dotProduct(double[] a, double[] b){
        assert(a.length == b.length);
        double scalar = 0.0;
        for(int i = 0; i < a.length; i++){
            scalar += a[i] * b[i];
        }
        return scalar;
    }

    public static double dotProduct(ArrayList<Double> a, ArrayList<Double> b){
        assert(a.size() == b.size());
        double scalar = 0.0;
        for(int i = 0; i < a.size(); i++){
            scalar += a.get(i) * b.get(i);
        }
        return scalar;
    }
}
