package com.techkid.anh82.truyenratngan;

/**
 * Created by anh82 on 4/21/2017.
 */

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.techkid.anh82.truyenratngan.databases.models.Lich;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.BitSet;



public class MyArrayAdapter extends ArrayAdapter<Lich>
{
    Bitmap bmp;
    Activity context=null;
    ArrayList<Lich>myArray=null;
    int layoutId;
    /**
     * Constructor này dùng để khởi tạo các giá trị
     * từ MainActivity truyền vào
     * @param context : là Activity từ Main
     * @param layoutId : Là layout custom do ta tạo (my_item_layout.xml)
     * @param arr : Danh sách nhân viên truyền từ Main
     */
    public MyArrayAdapter(Activity context, int layoutId, ArrayList<Lich>arr){
        super(context, layoutId, arr);
        this.context=context;
        this.layoutId=layoutId;
        this.myArray=arr;
    }
    /**
     * hàm dùng để custom layout, ta phải override lại hàm này
     * từ MainActivity truyền vào
     * @param position : là vị trí của phần tử trong danh sách nhân viên
     * @param convertView: convertView, dùng nó để xử lý Item
     * @param parent : Danh sách nhân viên truyền từ Main
     * @return View: trả về chính convertView
     */


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        /**
         * bạn chú ý là ở đây Tôi không làm:
         * if(convertView==null)
         * {
         * LayoutInflater inflater=
         * context.getLayoutInflater();
         * convertView=inflater.inflate(layoutId, null);
         * }
         * Lý do là ta phải xử lý xóa phần tử Checked, nếu dùng If thì
         * nó lại checked cho các phần tử khác sau khi xóa vì convertView
         * lưu lại trạng thái trước đó
         */
        LayoutInflater inflater=  context.getLayoutInflater();
        convertView=inflater.inflate(layoutId, null);
        //chỉ là test thôi, bạn có thể bỏ If đi
        if(myArray.size()>0 && position>=0)
        {
            //dòng lệnh lấy TextView ra để hiển thị Mã và tên lên
            final Lich emp = myArray.get(position);
            final TextView txtdisplay=(TextView) convertView.findViewById(R.id.content);
            final ImageView imageView=(ImageView) convertView.findViewById(R.id.image);


            //lấy ra nhân viên thứ position

            //đưa thông tin lên TextView
            //emp.toString() sẽ trả về Id và Name
            txtdisplay.setText(emp.toString());

            Picasso.with(context)
                    //Link ảnh cần load
                    .load(emp.getImage())
                    // optional
                    .placeholder(R.mipmap.ic_launcher)
                    //Nếu không load được
                    .error(R.mipmap.ic_launcher)
                    //Gán ảnh lên ImageView
                    .into(imageView);



        }
        //Vì View là Object là dạng tham chiếu đối tượng, nên
        //mọi sự thay đổi của các object bên trong convertView
        //thì nó cũng biết sự thay đổi đó
        return convertView;//trả về View này, tức là trả luôn
        //về các thông số mới mà ta vừa thay đổi
    }
    private class loadImageFromIternet extends AsyncTask<String,Integer, String> {
        ImageView iv ;
        public loadImageFromIternet(ImageView iv) {
            this.iv = iv;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                URL u1 =  new URL(params[0]); URL u2 =  new URL(params[1]);

                bmp = BitmapFactory.decodeStream(u1.openConnection().getInputStream());


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        // @Override
        protected void onPostExecute(String s){
            // img1.setImageBitmap(bmp);
            iv.setImageBitmap(bmp);

        }
    }
}
