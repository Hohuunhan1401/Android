package com.example.danhbai;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int manghinhbai[] = {
            R.drawable.c10, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5,
            R.drawable.c6, R.drawable.c7, R.drawable.c8, R.drawable.c9, R.drawable.c10,
            R.drawable.cj, R.drawable.cq, R.drawable.ck,
            R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5,
            R.drawable.d6, R.drawable.d7, R.drawable.d8, R.drawable.d9, R.drawable.d10,
            R.drawable.dj, R.drawable.dq, R.drawable.dk,
            R.drawable.h1, R.drawable.h2, R.drawable.h3, R.drawable.h4, R.drawable.h5,
            R.drawable.h6, R.drawable.h7, R.drawable.h8, R.drawable.h9, R.drawable.h10,
            R.drawable.hj, R.drawable.hq, R.drawable.hk,
            R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5,
            R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10,
            R.drawable.sj, R.drawable.sq, R.drawable.sk};
    CountDownTimer Timer;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView igvM1 = findViewById(R.id.imageView1);
        ImageView igvM2 = findViewById(R.id.imageView2);
        ImageView igvM3 = findViewById(R.id.imageView3);

        ImageView igvM11 = findViewById(R.id.imageView11);
        ImageView igvM22 = findViewById(R.id.imageView22);
        ImageView igvM33 = findViewById(R.id.imageView33);

        TextView tv_M = findViewById(R.id.tv_kqM);
        TextView tv_M2 = findViewById(R.id.tv_kqM2);

        TextView may = findViewById(R.id.May);
        TextView may2 = findViewById(R.id.May2);

        Button choi = findViewById(R.id.btn_play);

        EditText tg = findViewById(R.id.editText_TimeStart);
        EditText buoc = findViewById(R.id.editText_Step);

        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        myDialog.setTitle("Ket Qua");

        myDialog.setPositiveButton("Choi Tiep", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tg.setText("");
                buoc.setText("");
                Toast.makeText(MainActivity.this, "Luot Choi Moi", Toast.LENGTH_SHORT).show();
                recreate();
            }
        });
        myDialog.setNegativeButton("Thoat", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Thoat khoi chuong trinh", Toast.LENGTH_SHORT).show();
                finish();
            }
        });



        AlertDialog.Builder canhbao = new AlertDialog.Builder(this);
        canhbao.setTitle("Canh bao").setMessage("Chua cap nhat thoi gian");

        canhbao.setPositiveButton("Dat thoi gian", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        canhbao.setNegativeButton("Thoat", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Thoat khỏi chuong trinhh", Toast.LENGTH_SHORT).show();
                finish();
            }
        });




        //Set onclick PLAY
        choi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tg.length()==0 || buoc.length()==0) {
                    AlertDialog dialog = canhbao.create();
                    dialog.show();
                } else {
                    Timer = new CountDownTimer(Integer.parseInt(tg.getText().toString()) * 1000,
                            Integer.parseInt(buoc.getText().toString()) * 1000) {
                        @Override
                        public void onTick(long l) {
                            int mayW = Integer.parseInt(may.getText().toString().trim());
                            int may2W = Integer.parseInt(may2.getText().toString().trim());
                            int chiabai[] = lay6songaunhien(0, 51);

                            int valueMay[] = {chiabai[0], chiabai[2], chiabai[4]};
                            igvM1.setImageResource(manghinhbai[valueMay[0]]);
                            igvM2.setImageResource(manghinhbai[valueMay[1]]);
                            igvM3.setImageResource(manghinhbai[valueMay[2]]);
                            tv_M.setText(tinhketqua(valueMay));

                            int valueMay2[] = {chiabai[1], chiabai[3], chiabai[5]};
                            igvM11.setImageResource(manghinhbai[valueMay2[0]]);
                            igvM22.setImageResource(manghinhbai[valueMay2[1]]);
                            igvM33.setImageResource(manghinhbai[valueMay2[2]]);
                            tv_M2.setText(tinhketqua(valueMay2));

                            int nutMay = tinhSoNut(valueMay);
                            int nutMay2 = tinhSoNut(valueMay2);

                            if (nutMay > nutMay2) {
                                mayW += 1;
                                may.setText(String.valueOf(mayW));
                            } else if (nutMay < nutMay2) {
                                may2W += 1;
                                may2.setText(String.valueOf(may2W));
                            }

                        }

                        @Override
                        public void onFinish() {
                            int mayW = Integer.parseInt(may.getText().toString().trim());
                            int nguoiW = Integer.parseInt(may2.getText().toString().trim());
                            if (mayW > nguoiW) {
                                String mes = "May 1 WIN\n" + "May 1: " + mayW + "\n" + "May 2: " + nguoiW;
                                myDialog.setMessage(mes);
                                AlertDialog dialog = myDialog.create();
                                dialog.show();
                            } else if (nguoiW > mayW) {
                                String mes = "May 2 WIN\n" + "May 2: " + nguoiW + "\n" + "May 1: " + mayW;
                                myDialog.setMessage(mes);
                                AlertDialog dialog = myDialog.create();
                                dialog.show();
                            } else {
                                String mes = "DRAW\n" + "May 2: " + nguoiW + "\n" + "May 1: " + mayW;
                                myDialog.setMessage(mes);
                                AlertDialog dialog = myDialog.create();
                                dialog.show();
                            }
                        }
                    }.start();

                }

            }
        });
    }


    // Lay 3 so ngau nhien khong trung nhau
    private int[] lay3songaunhien(int min, int max) {
        int[] baso = new int[3];
        int i = 0;
        baso[i++] = random(min, max);
        do {
            int k = random(min, max);
            if (!ktratrung(k, baso))
                baso[i++] = k;
        } while (i < 3);
        return baso;
    }

    // lay 6 so ngau nhien khong trung nhau
    private int[] lay6songaunhien(int min, int max) {
        int[] baso = new int[6];
        int i = 0;
        baso[i++] = random(min, max);
        do {
            int k = random(min, max);
            if (!ktratrung(k, baso))
                baso[i++] = k;
        } while (i < 6);
        return baso;
    }

    //chon ngau nhien trong khoan duoc xac dinh
    private int random(int min, int max) {
        return min + (int) (Math.random() * (max - min + 1));
    }

    //kiem tra gia tri bi trung nhau
    private boolean ktratrung(int k, int[] a) {
        for (int i = 0; i < a.length; i++)
            if (a[i] == k)
                return true;
        return false;
    }

    //tinh so la bay Tay
    private int tinhsoTay(int[] a) {
        int k = 0;
        for (int i = 0; i < a.length; i++)
            if (a[i] % 13 >= 10 && a[i] % 13 < 13)
                k++;
        return k;
    }

    //Tinh ket qua cua 3 la bai rut duoc
    private String tinhketqua(int[] baso) {
        String KQ = "";
        if (tinhsoTay(baso) == 3)
            KQ = "Kết Quả: 3 tây";
        else {
            int tong = 0;
            for (int i = 0; i < baso.length; i++)
                if (baso[i] % 13 < 10)
                    tong += baso[i] % 13 + 1;
            if (tong % 10 == 0)
                KQ = "Bù, trong đó có " + tinhsoTay(baso) + " tây";
            else
                KQ = "Kết quả là " + tong % 10 + " nút, trong đó có " + tinhsoTay(baso) + " tây";
        }
        return KQ;
    }

    private int tinhSoNut(int[] baso) {
        if (tinhsoTay(baso) == 3) {
            return 10;
        } else {
            int tong = 0;
            for (int i = 0; i < baso.length; i++)
                if (baso[i] % 13 < 10)
                    tong += baso[i] % 13 + 1;
            if (tong % 10 == 0)
                return 0;
            else
                return tong % 10;
        }
    }
}