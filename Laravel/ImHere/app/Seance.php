<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Seance extends Model
{
    public $timestamps = false;
    protected $fillable = ["slug","seanceNb","local","hour","duration","date"];
}
