package com.example.whensaturdaycome.persuratan;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.internal.deps.guava.util.concurrent.SettableFuture;

import com.example.whensaturdaycome.persuratan.callback.InformasiCallBack;
import com.example.whensaturdaycome.persuratan.callback.SuratKeluarCallBack;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;

public class TestInformasi {

    String datanya = "{\"status\":true,\"data\":[{\"id_informasi\":\"1\",\"id_user\":\"1\",\"judul\":\"Rapat kordinasi maklumat\",\"waktu\":\"2018-05-10 08:00:00\",\"isi\":\"mengharap kehadiran dari tim untuk menghadiri rapat kodrinasi di ruang rapat.\"}]}";
    Context context;

    @Before
    public void Test(){

        context = InstrumentationRegistry.getContext();
    }

    @Test
    public void useTestDisimpan() throws Exception{

        InformasiCallBack masuk = new InformasiCallBack (context);

        final SettableFuture<String> ff = SettableFuture.create();

        masuk.InformasiCallBack(new InformasiCallBack.InformasiBack() {

            @Override
            public void hasil(String hasil) {

                ff.set(hasil);
            }
        });

        String isi = ff.get(5, TimeUnit.SECONDS);
        assertEquals(isi, datanya);
    }
}
