package fr.best.client.clientbest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import fr.best.client.entity.Point;
import fr.best.client.entity.Zone;

import fr.best.client.entity.Zone;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sila on 15/03/2018.
 */

public class VuePlan extends View {

    private Bitmap mImage;

    public static Point point=null;

    ///sila
    private List<Zone> listeZone;
    private Paint paint;

    private Point positionDepart;
    private Point newPosition;
    private Point sortie;

    private int i=0;
    private List<Point> listePoints;

    public static boolean valideZone;

    public VuePlan(Context context) {
        super(context);

        init(null);
    }

    public VuePlan(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public VuePlan(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    public VuePlan(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs){

        paint = new Paint();
        paint.setARGB(255,41,154,228);
        paint.setStrokeWidth(50);

        if(MainActivity.nomBatiment.equals("Prunais")) {
            mImage = BitmapFactory.decodeResource(getResources(), R.drawable.home5);
        }else if (MainActivity.nomBatiment.equals("Algeco")){
            mImage = BitmapFactory.decodeResource(getResources(), R.drawable.imagecopernic);
        }else {
            mImage = BitmapFactory.decodeResource(getResources(), R.drawable.classe);
        }

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN)
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);

                mImage=resizeImage(mImage,getWidth(),getHeight());
            }
        });

        positionDepart=null;
        newPosition=null;
        sortie=null;
        valideZone=false;

        listeZone=new ArrayList<>();
        listePoints=new ArrayList<>();
        this.setOnTouchListener(listener);
    }

    public Zone retournerZone(Point position){

        Zone zoneCourante=null;

        if(position==null) System.out.println("Poisition nulle");
        else {

            for (Zone z : listeZone) {
                if (z.estDanslaZone(position)) {
                    zoneCourante = z;
                }
            }
        }
        return zoneCourante;
    }

    public void naviguer(Point position,Point sortie){

        this.positionDepart=position;
        this.newPosition=position;
        this.sortie=sortie;

        // Les points des zones
        Point point1=new Point(30,80);
        Point point2=new Point(30,1330);
        Point point3=new Point(465,80);
        Point point4=new Point(465,1330);
        Point point5=new Point(710,80);
        Point point6=new Point(710,1330);
        Point point7=new Point(1160,80);
        Point point8=new Point(1160,1330);

        listePoints.add(point1);
        listePoints.add(point2);
        listePoints.add(point3);
        listePoints.add(point4);
        listePoints.add(point5);
        listePoints.add(point6);
        listePoints.add(point7);
        listePoints.add(point8);

        delimiterZone();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    View.OnTouchListener listener = new View.OnTouchListener(){

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            int action = motionEvent.getActionMasked();

            if(action==MotionEvent.ACTION_DOWN){

                // Le point touché sur le canvas
                point = new Point(motionEvent.getX(),motionEvent.getY());

                System.out.println("POINT STATIC: "+point.getX()+" , "+point.getY());

            }

            return true;
        }
    };

    private void delimiterZone(){

        Zone zone1 = new Zone("SALLES","X","Droite");
        zone1.delimiterZone(listePoints.get(0),listePoints.get(1),listePoints.get(2),listePoints.get(3));
        listeZone.add(zone1);

        Zone zone2 = new Zone("COULOIR","Y");
        zone2.delimiterZone(listePoints.get(2),listePoints.get(3),listePoints.get(4),listePoints.get(5));
        zone2.setZoneSuivante("SORTIE");
        listeZone.add(zone2);

        Zone zone3 = new Zone("SALLES","X","Gauche");
        zone3.delimiterZone(listePoints.get(4),listePoints.get(5),listePoints.get(6),listePoints.get(7));
        listeZone.add(zone3);

    }


    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.drawBitmap(mImage,0,0,null);
        // DESSIN DU CHEMIN
        if(valideZone) {

            Zone z = retournerZone(newPosition);

            if(z==null) System.out.println("NULL CETTE ZONE");
            else {
                while (retournerZone(newPosition).getZoneSuivante() != "SORTIE") {

                    if (z.getDirectionMarche() == "X") {
                        if (z.getSensDirection() == "Droite") {
                            this.newPosition = new Point(newPosition.getX() + 20, newPosition.getY());
                            canvas.drawLine(positionDepart.getX(), positionDepart.getY(), newPosition.getX(), newPosition.getY(), paint);

                        } else {
                            this.newPosition = new Point(newPosition.getX() - 20, newPosition.getY());
                            canvas.drawLine(positionDepart.getX(), positionDepart.getY(), newPosition.getX(), newPosition.getY(), paint);

                        }

                    } else {
                        positionDepart = newPosition;
                        if (sortie.getY() < newPosition.getY()) {
                            this.newPosition = new Point(newPosition.getX(), newPosition.getY() - 20);
                            canvas.drawLine(positionDepart.getX(), positionDepart.getY(), newPosition.getX(), newPosition.getY(), paint);
                        } else {
                            this.newPosition = new Point(newPosition.getX(), newPosition.getY() + 20);
                            canvas.drawLine(positionDepart.getX(), positionDepart.getY(), newPosition.getX(), newPosition.getY(), paint);
                        }
                    }


                }

                canvas.drawLine(newPosition.getX(), newPosition.getY(), sortie.getX(), sortie.getY(), paint);

            }


        }
    }

    private Bitmap resizeImage(Bitmap image,int width,int height)
    {
        Matrix matrix=new Matrix();

        RectF src=new RectF(0,0,image.getWidth(),image.getHeight());
        RectF dst=new RectF(0,0,width,height);

        matrix.setRectToRect(src,dst, Matrix.ScaleToFit.FILL);

        return Bitmap.createBitmap(image,0,0,image.getWidth(),image.getHeight(),matrix,true);

    }


}
