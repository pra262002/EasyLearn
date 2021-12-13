package com.example.language.View;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.language.Attempt_Quiz_Section.Tests;
import com.example.language.Auth_Controller.LoginActivity;
import com.example.language.Auth_Controller.ResetPasswordActivity;
import com.example.language.Model.User;
import com.example.language.R;
import com.example.language.Results_section.ResultsAdmin;
import com.example.language.Splash_Activity.SplashAnimation;
import com.example.language.german.ColorsActivity;
import com.example.language.german.MonthsActivity;
import com.example.language.german.NumbersActivity;
import com.example.language.german.SentencesActivity;
import com.example.language.german.SimpleWordActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private DatabaseReference myRef;
    public TextView USer_email;
    public ImageButton imageButton;
    private StorageReference firebaseStorage;
    private boolean isAdmin=false;
    private boolean isUser=false;
    private TextView userID;
    private CircleImageView imageView;
    public CircleImageView imageView1;
    final long ONE_MEGABYTE = 1024 * 1024;
    private FloatingActionButton floatingActionButton;

   public Button numbers,colors,things,sentences,months;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        imageButton = findViewById(R.id.userImage2);
        setSupportActionBar(toolbar);
        numbers = findViewById(R.id.number_button);

        //Get Button View from color id
       colors = findViewById(R.id.color_button);
       months = findViewById(R.id.month_button);
       things = findViewById(R.id.things_button);
         sentences = findViewById(R.id.sentence_button);
        imageView = findViewById(R.id.card1);
        floatingActionButton = findViewById(R.id.chatHead);
        auth= FirebaseAuth.getInstance();

        //get user image if it's available
        firebaseStorage = FirebaseStorage.getInstance().getReference(Objects.requireNonNull(auth.getUid()));
        firebaseStorage.getBytes(ONE_MEGABYTE)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        DisplayMetrics dm = new DisplayMetrics();
                        getWindowManager().getDefaultDisplay().getMetrics(dm);

                        imageView.setMinimumHeight(150);
                        imageView.setMinimumWidth(150);
                        imageView.setMaxHeight(150);
                        imageView.setMaxWidth(150);
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imageView.setImageBitmap(bm);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
        //fragment for term & conditions!
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetFragment sheetFragment = new BottomSheetFragment();
                sheetFragment.show(getSupportFragmentManager(),sheetFragment.getTag());
            }
        });

        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent to open number activity
                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(numbersIntent);
                Toast.makeText(getApplicationContext(),"Numbers Selected",Toast.LENGTH_SHORT).show();

            }
        });
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent to open colors activity
                Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(colorsIntent);
                Toast.makeText(getApplicationContext(),"Colors Selected",Toast.LENGTH_SHORT).show();
            }
        });

        months.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent monthsIntent = new Intent(MainActivity.this, MonthsActivity.class);
                startActivity(monthsIntent);
                Toast.makeText(getApplicationContext(),"Months Selected",Toast.LENGTH_SHORT).show();

            }
        });

        things.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent thingsIntent = new Intent(MainActivity.this, SimpleWordActivity.class);
                startActivity(thingsIntent);
                Toast.makeText(getApplicationContext(),"Things Selected",Toast.LENGTH_SHORT).show();

            }
        });

        sentences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sentencesIntent = new Intent(MainActivity.this, SentencesActivity.class);
                startActivity(sentencesIntent);
                Toast.makeText(getApplicationContext(),"Sentences Selected",Toast.LENGTH_SHORT).show();

            }
        });

        auth = FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        myRef=database.getReference();

        /*check if user is admin or not**/
        //TODO
       // checkForAdmin();

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        /*set profile_pic of user on navigation drawer**/

        NavigationView navigationView =  findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        imageView1 = (navigationView.getHeaderView(0)).findViewById(R.id.imageView);
        USer_email = header.findViewById(R.id.text_user_name);
        setTextOnUser();
        navigationView.setNavigationItemSelectedListener(this);
        userID = findViewById(R.id.text_user_card);
        setUserEmail();
        setImageOnNavHeader();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SplashAnimation.class);
                intent.putExtra("ChatAdmin",isUser);
                startActivity(intent);
            }
        });
    }

//    public void checkForAdmin() {
//
//        myRef.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.child(Objects.requireNonNull(auth.getUid()))
//                        .exists()&& Objects.requireNonNull(dataSnapshot.child(auth.getUid())
//                        .getValue()).toString().equals("true")){
//                    isUser=false;
//                    Toasty.info(getApplicationContext(),"Hello", Toasty.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

    public void setImageOnNavHeader() {

        firebaseStorage = FirebaseStorage.getInstance().getReference(Objects.requireNonNull(auth.getUid()));
        firebaseStorage.getBytes(ONE_MEGABYTE)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        DisplayMetrics dm = new DisplayMetrics();
                        getWindowManager().getDefaultDisplay().getMetrics(dm);

                        imageView1.setMinimumHeight(90);
                        imageView1.setMinimumWidth(90);
                        imageView1.setMaxHeight(100);
                        imageView1.setMaxWidth(100);
                        imageView1.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imageView1.setImageBitmap(bm);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
            int id = item.getItemId();

            if (id == R.id.nav_test) {
                if (isNetworkAvailable(MainActivity.this)) {
                    startActivity(new Intent(MainActivity.this, Tests.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
                else
                    alertNoConnection();
            } else if (id == R.id.nav_result) {
                if ( isNetworkAvailable(MainActivity.this)) {
                    Intent intent = new Intent(MainActivity.this, ResultsAdmin.class);
                    intent.putExtra("ISUSER",isUser);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
                else
                    alertNoConnection();
//            } else if (id == R.id.create_test) {
//                if (isNetworkAvailable(MainActivity.this)) {
//                    startActivity(new Intent(MainActivity.this, ColorsActivity.class));
//                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//                }
//                else if (isNetworkAvailable(MainActivity.this))
//                    Toasty.error(getApplicationContext(), "You are not Admin!", Toasty.LENGTH_SHORT).show();
                //else
                  //  alertNoConnection();
            } else if (id == R.id.nav_respass) {
                if (isNetworkAvailable(MainActivity.this)) {
                    startActivity(new Intent(MainActivity.this, ResetPasswordActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
                else
                    alertNoConnection();
            } else if (id == R.id.nav_signout) {
                auth.signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } else if (id == R.id.nav_details) {
                startActivity(new Intent(MainActivity.this, AddDetails.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
//            else if (id == R.id.nav_admin) {
//                startActivity(new Intent(MainActivity.this, TobeAdmin.class));
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//            }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void Homepage (){
    Button numbers = findViewById(R.id.number_button);

    //Get Button View from color id
    Button colors = findViewById(R.id.color_button);
    Button months = findViewById(R.id.month_button);
    Button things = findViewById(R.id.things_button);
    Button sentences = findViewById(R.id.sentence_button);

        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent to open number activity
                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(numbersIntent);
                Toast.makeText(getApplicationContext(),"Numbers Selected",Toast.LENGTH_SHORT).show();

            }
        });
}

    //set textView on MainActivity
    public void setUserEmail() {
         DatabaseReference mDatabase;
         FirebaseAuth auth;
         mDatabase = FirebaseDatabase.getInstance().getReference();
         auth= FirebaseAuth.getInstance();
        mDatabase.child("users").child(Objects.requireNonNull(auth.getUid()))
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    User user=dataSnapshot.getValue(User.class);
                    String temp = " What's up! " + user.name ;
                    userID.setText(temp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void setTextOnUser(){
        FirebaseUser usero = FirebaseAuth.getInstance().getCurrentUser();
        USer_email.setText(Objects.requireNonNull(usero).getEmail());
    }

    /*method to handle network connection**/
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public void alertNoConnection() {

        /*final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setIcon(R.drawable.nowifi);
        builder.setCancelable(true);
        builder.setTitle("No Connection Available!");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();*/

        Dialog builder = new Dialog(this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                //nothing;
            }
        });

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.nowifi);
//        imageView.getLayoutParams().height = 100;
//        imageView.getLayoutParams().width = 100;
//        imageView.requestLayout();
        //imageView.setImage(R.drawable.nowifi);
        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                400,
                400));
        builder.show();
    }

}
