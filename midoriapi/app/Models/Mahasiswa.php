<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Mahasiswa extends Model
{
    use HasFactory;
    protected $table="mahasiswa";
    protected $fillable = [
    'nim_mahasiswa',
    'nama_mahasiswa',
    'angkatan_mahasiswa',
    'kd_prodi'
];

}
