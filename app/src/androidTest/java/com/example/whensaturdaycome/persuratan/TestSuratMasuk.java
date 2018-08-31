package com.example.whensaturdaycome.persuratan;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.internal.deps.guava.util.concurrent.SettableFuture;

import com.example.whensaturdaycome.persuratan.callback.SuratKeluarCallBack;
import com.example.whensaturdaycome.persuratan.callback.SuratMasukCallBack;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;

public class TestSuratMasuk {

    String datanya = "{\"status\":true,\"data\":[{\"id_surat_masuk\":\"1\",\"id_jenis\":\"1\",\"tgl_diterima\":\"2018-05-15\",\"no_agenda\":\"1050\",\"pengirim\":\"MPM PP\",\"nama_surat\":\"Dinas\",\"tgl_surat\":\"2018-05-13\",\"perihal\":\"Permohonan memberikan sambutan\",\"id_sifat\":\"1\",\"id_macam\":\"2\",\"id_user\":\"2\",\"file\":\"suratresmi.jpg\",\"disposisi\":\"revisi\",\"catatan\":\"segera di prosess\"},{\"id_surat_masuk\":\"2\",\"id_jenis\":\"1\",\"tgl_diterima\":\"2018-08-07\",\"no_agenda\":\"1872\",\"pengirim\":\"Dinas Sosial\",\"nama_surat\":\"Permohonan kerjasama\",\"tgl_surat\":\"2018-08-02\",\"perihal\":\"mengharap kehadiran\",\"id_sifat\":\"2\",\"id_macam\":\"2\",\"id_user\":\"3\",\"file\":\"suratku.jpg\",\"disposisi\":\"diterima\",\"catatan\":\"mohon di pertimbangkan]\"}]}";
    Context context;

    @Before
    public void Test(){

        context = InstrumentationRegistry.getContext();
    }

    @Test
    public void useTestDisimpan() throws Exception{

        SuratMasukCallBack masuk = new SuratMasukCallBack (context);

        final SettableFuture<String> ff = SettableFuture.create();

        masuk.SuratMasukCallBack(new SuratMasukCallBack.SuratMasukBack() {

            @Override
            public void hasil(String hasil) {

                ff.set(hasil);
            }
        });

        String isi = ff.get(5, TimeUnit.SECONDS);
        assertEquals(isi, datanya);
    }
}
