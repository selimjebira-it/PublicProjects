<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Presence extends Model
{
    public $timestamps = false;
    protected $fillable = ["studentId","seanceId"];
}
