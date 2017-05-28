import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Main {
	static final String AUTH = "Client-ID 6676c6a29041d49";
	static final String PATH = "dog.jpg";
	static final ImgurAPI imgurApi = createImgurAPI();
	public static void main(String[] args) {
		try{
			File image = new File(PATH);
			RequestBody request = RequestBody.create(MediaType.parse("image/*"), image);
			Call<ImageResponse> call =  imgurApi.postImage(request);
			Response<ImageResponse> res = call.execute();
			
			System.out.println("是否成功: " + res.isSuccessful());
			System.out.println(res.body().data.link);	
		}catch(Exception err){
			err.printStackTrace();
		}
	}
	
	static ImgurAPI createImgurAPI(){
		Retrofit retrofit = new Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create())
			    .baseUrl(ImgurAPI.SERVER)
			    .build();
		return retrofit.create(ImgurAPI.class);
	}

}
