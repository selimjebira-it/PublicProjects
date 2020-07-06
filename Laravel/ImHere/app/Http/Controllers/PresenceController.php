<?php

namespace App\Http\Controllers;

use App\Presence;
use App\Student;
use Illuminate\Http\Request;
use Illuminate\support\Facades\DB;


class PresenceController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $presences = DB::table('presences')
        ->join("students","students.id","=","presences.studentId")
        ->join("seances","seances.id","=","presences.seanceId")
        ->join("courses","courses.slug","=","seances.slug")
        ->select("students.name","students.surname","courses.slug","seances.seanceNb")
        ->get();
        
        
        return view('imhere.presence.presences',compact("presences"));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $validation = $request->validate([
            'seanceId'=>'required|exists:seances,id',
        ]);
        
        $seanceId = $request->get("seanceId");
        $studentsId = $request->get("studentIds");
        
        foreach($studentsId as $studentId){
            $presence = new Presence(["seanceId"=>$seanceId, "studentId"=>$studentId]);
            $presence->save();

        }
        return redirect("/presences")->with('succes',"presences has been added");
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Presence  $presence
     * @return \Illuminate\Http\Response
     */
    public function show(Presence $presence)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Presence  $presence
     * @return \Illuminate\Http\Response
     */
    public function edit(Presence $presence)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Presence  $presence
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, Presence $presence)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Presence  $presence
     * @return \Illuminate\Http\Response
     */
    public function destroy(Presence $presence)
    {
        $presence->delete();
    }
}
