import java.util.Random;

/**
 * Created by Lysergids on 2016/5/26 0026.
 */
public class reduceTimeRatio {

    private int projectNum;
    int[]  ratio;
    float[] ratioF;
    Random random;
    public reduceTimeRatio(int projectNum){
        this.projectNum = projectNum;
    }

    public void result(){
        random = new Random();
        ratio = new int[projectNum];
        ratioF = new float[projectNum];
        for (int i = 0; i < projectNum; i++){
            ratio[i] = random.nextInt(projectNum*30);
            ratioF[i]=0.00000f;
        }
        int temp = 0;
        for(int i=1;i<ratio.length;i++)
        {
            for(int j=0;j<ratio.length-i;j++)
            {
                if(ratio[j]>ratio[j+1])
                {
                    temp=ratio[j];
                    ratio[j]=ratio[j+1];
                    ratio[j+1]=temp;
                }
            }
        }
        float sum = 0.0f;
        for(int i=0;i<ratio.length;i++){
            sum += ratio[i];
            //System.out.println(sum);
        }
        for(int i=0;i<ratioF.length;i++){
            ratioF[i]=ratio[i]/sum;
            //System.out.println(ratioF[i]);
        }

    }

    public float[] above(){
        return ratioF;
    }

    public  float[] below(float ratio){
        for(int i = 0; i < projectNum; i++){
            ratioF[i] = ratioF[i] * ratio;
        }
        return ratioF;
    }
}
