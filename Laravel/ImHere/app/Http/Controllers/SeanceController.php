<?php

namespace App\Http\Controllers;

use App\Seance;
use App\Course;
use App\Student;
use App\Presence;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class SeanceController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $seances = Seance::all();
        return view('imhere.seance.seances',compact("seances"));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $courses = Course::all();
        return view('imhere.seance.create',compact("courses"));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $validate = $request->validate([
            'slug'=>'required|exists:courses,slug',
            'seanceNb'=>'required|numeric|min:0|not_in:0',
            'local' => 'required|max:15',
            'date'=>'required|after_or_equal:today',
            'hour'=>'required',
            'duration'=>'required'
        ]);
        Seance::create($request->all());
        return redirect('/seances')->with('success','seance has been added');
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Seance  $seance
     * @return \Illuminate\Http\Response
     */
    public function show(Seance $seance)
    {
        $students = Student::all();
        return view("imhere.seance.managing",compact("seance","students"));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Seance  $seance
     * @return \Illuminate\Http\Response
     */
    public function edit(Seance $seance)
    {
        $courses = Course::all();
        return view("imhere.seance.create",compact("seance","courses"));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Seance  $seance
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, Seance $seance)
    {
        $validate = $request->validate([
            'slug'=>'required|exists:courses,slug',
            'seanceNb'=>'required',
            'local' => 'required',
            'date'=>'required|date_format:d/m/Y|after_or_equal:today',
            'hour'=>'required',
            'duration'=>'required'
        ]);
        $seance->update($request->all());
        return redirect('/seances')->with('success','seance has been edited');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Seance  $seance
     * @return \Illuminate\Http\Response
     */
    public function destroy(Seance $seance)
    {
        $presences = DB::table('presences')
        ->join("seances","seanceId","=","seances.id")
        ->where("seanceId",$seance->id)
        ->select("presences.id")
        ->get();
        foreach($presences as $presence){
            $p = Presence::find($presence->id);
            $p->delete();
        }
        $seance->delete();
        return redirect('/seances')->with('success','seance has been deleted');
    }
}
