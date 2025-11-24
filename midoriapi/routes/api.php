<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

use App\Http\Controllers\MahasiswaController;

Route::get('/mahasiswa', [MahasiswaController::class, 'index']);
Route::get('/mahasiswa/show/{id}', [MahasiswaController::class, 'show']);
Route::delete('/mahasiswa/destroy/{id}', [MahasiswaController::class, 'destroy']);
Route::post('/mahasiswa', [MahasiswaController::class, 'store']);
Route::put('/mahasiswa/update/{id}', [MahasiswaController::class, 'update']);