package com.example.whensaturdaycome.persuratan;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.internal.deps.guava.util.concurrent.SettableFuture;

import com.example.whensaturdaycome.persuratan.callback.DisposisiCallBack;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;

public class TestDisposisi {
    String datanya = "{\"status\":true,\"data\":[{\"id_surat_masuk\":\"1\",\"id_jenis\":\"1\",\"tgl_diterima\":\"2018-05-15\",\"no_agenda\":\"1050\",\"pengirim\":\"MPM PP\",\"nama_surat\":\"Dinas\",\"tgl_surat\":\"2018-05-13\",\"perihal\":\"Permohonan memberikan sambutan\",\"id_sifat\":\"1\",\"id_macam\":\"2\",\"id_user\":\"2\",\"file\":\"suratresmi.jpg\",\"disposisi\":\"diterima\",\"catatan\":\"tindak lanjut\"},{\"id_surat_masuk\":\"3\",\"id_jenis\":\"3\",\"tgl_diterima\":\"2018-08-12\",\"no_agenda\":\"6789\",\"pengirim\":\"pcm sruweng\",\"nama_surat\":\"permohonan\",\"tgl_surat\":\"2018-08-09\",\"perihal\":\"bantuan permohonan dana\",\"id_sifat\":\"1\",\"id_macam\":\"3\",\"id_user\":\"2\",\"file\":\"tembusan.jpg\",\"disposisi\":\"revisi\",\"catatan\":\"kembalikan\"}]}";
    Context context;

    @Before
    public void Test(){

        context = InstrumentationRegistry.getContext();
    }

    @Test
    public void useTestDisimpan() throws Exception{

        DisposisiCallBack masuk = new DisposisiCallBack (context);

        final SettableFuture<String> ff = SettableFuture.create();

        masuk.DisposisiCallBack(id, new DisposisiCallBack.DisposisiBack() {

            @Override
            public void hasil(String hasil) {

                ff.set(hasil);
            }
        });

        String isi = ff.get(5, TimeUnit.SECONDS);
        assertEquals(isi, datanya);
    }
}
