package com.example.whensaturdaycome.persuratan;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.internal.deps.guava.util.concurrent.SettableFuture;

import com.example.whensaturdaycome.persuratan.callback.SuratKeluarCallBack;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;

public class TestSuratKeluar {

    String datanya = "{\"status\":true,\"data\":[{\"id_surat_keluar\":\"1\",\"id_kelompok\":\"1\",\"tgl_surat\":\"2018-05-08\",\"no_surat\":\"12123\\/66\\/1231\\/2018\",\"tujuan\":\"Rektor UMY\",\"perihal\":\"Rekomendasi Jokosusilo\",\"tembusan\":\"PP Kantor Jakarta, PWIM DIY\",\"file\":\"suratresmi.jpg\",\"catatan\":\"Harap diterima\"}]}";
    Context context;

    @Before
    public void Test(){

        context = InstrumentationRegistry.getContext();
    }

    @Test
    public void useTestDisimpan() throws Exception{

        SuratKeluarCallBack masuk = new SuratKeluarCallBack (context);

        final SettableFuture<String> ff = SettableFuture.create();

        masuk.SuratKeluarCallBack(new SuratKeluarCallBack.SuratKeluarBack() {

            @Override
            public void hasil(String hasil) {

                ff.set(hasil);
            }
        });

        String isi = ff.get(5, TimeUnit.SECONDS);
        assertEquals(isi, datanya);
    }
}
