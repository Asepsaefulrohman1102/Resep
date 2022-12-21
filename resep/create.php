<?php

require("koneksi.php");
$response=array();

 if($_SERVER['REQUEST_METHOD']==='POST')
 {
		
		//Mendapatkan Nilai Variable
		$id= $_POST['id'];
		$nama= $_POST['nama'];
		$nama_pembuat = $_POST['nama_pembuat'];
		$isi = $_POST['isi'];
		        
		
		$perintah = "INSERT INTO tb_resep (id,nama,nama_pembuat,isi) VALUES ('$id','$nama','$nama_pembuat','$isi')";
		$eksekusi=mysqli_query($konek,$perintah);
		$cek=mysqli_affected_rows($konek);
		
		if ($cek>0)
		{
			$response["kode"]=1;
			$response["pesan"]="Simpan data Berhasil";
		}
		else
		{
			$response["kode"]=0;
			$response["pesan"]="gagal";
			
		}
 }
 else
 {
	 $response["kode"]=0;
	 $response["pesan"]="tidak ada post data";
 }
 echo json_encode($response);
 mysqli_close($konek);
?>