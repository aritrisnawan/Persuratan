package com.example.whensaturdaycome.persuratan.data;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class ApiService {

    @FormUrlEncoded
    @POST("Surat_masuk.php")
    Call<ResponseBody> editData(@Field("id_surat_masuk") String id_surat_masuk, @Field("disposisi") String disposisi, @Field("catatan") String catatan) {
        return null;
    }
}
