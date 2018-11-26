package app.prasetya.com.booknow;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class InformationBooking extends AppCompatActivity {
    private android.support.v7.widget.Toolbar mToolbar;

    private Button mBtnGen,mBtnFinish;

    private TextView mTextView;
    private ImageView mQr;
    private String text2Qr;
    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_booking);
        initToolbar();

        String Room = getIntent().getStringExtra("ROOM");
        String Price = getIntent().getStringExtra("PRICE");
        String Date = getIntent().getStringExtra("DATE");
        String Time = getIntent().getStringExtra("TIME");
        String Name = getIntent().getStringExtra("NAME");
        String Phone = getIntent().getStringExtra("PHONE");


        mTextView = (TextView) findViewById(R.id.text_information);

        mTextView.setText(Room + "\n"+Price + "\n" + Date + "     " + Time +"\n" + Name + "\n" + Phone);

        mBtnGen = (Button) findViewById(R.id.btnGenerate);
        mBtnFinish = (Button) findViewById(R.id.btnExit);
        mQr = (ImageView) findViewById(R.id.qrCode);

        mBtnGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQr.setVisibility(View.VISIBLE);
                qrGenerate();

            }
        });

        mBtnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });


        notification(Room);
    }

    private void notification(String room) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentTitle("Anda Berhasil Menyewa sebuah Ruang Meeting")
                .setContentText(room);

        notificationBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());

        notificationManagerCompat.notify(1,notificationBuilder.build());
    }

    private void initToolbar() {
        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("Book Now");
    }

    private void qrGenerate() {
        text2Qr = mTextView.getText().toString();
        try{
            BitMatrix bitMatrix = multiFormatWriter.encode(text2Qr, BarcodeFormat.QR_CODE,300,300);
            BarcodeEncoder encode = new BarcodeEncoder();
            Bitmap bitmap = encode.createBitmap(bitMatrix);
            mQr.setImageBitmap(bitmap);
        }catch (WriterException e){
            e.printStackTrace();
        }
    }
}
