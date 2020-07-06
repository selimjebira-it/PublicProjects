<?php

namespace App\Http\Controllers;

use App\Student;
use App\presence;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class StudentController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $students = Student::all();
        return view("imhere.student.students",compact("students"));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view("imhere.student.create");
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
            'name' => "required|max:20",
            'surname'=> "required|max:20",
            'studentId'=>"required|numeric|unique:students|min:0|not_in:0"
        ]);
        $student = Student::create($request->all());
        return redirect('/students')->with("success","$student->name has been added!");
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Student  $student
     * @return \Illuminate\Http\Response
     */
    public function show(Student $student)
    {
        return view("imhere.student.show",compact("student"));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Student  $student
     * @return \Illuminate\Http\Response
     */
    public function edit(Student $student)
    {
        return view("imhere.student.create",compact("student"));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Student  $student
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, Student $student)
    {
        if($request->get("studentId") != $student->studentId){
            $validation = $request->validate([
                'name' => "required|max:20",
                'surname'=> "required|max:20",
                'studentId'=>"required|numeric|unique:students|min:0|not_in:0"
            ]);
        }else{
            $validation = $request->validate([
                'name' => "required|max:20",
                'surname'=> "required|max:20",
            ]);
        }
        
        $student->update($request->all());
        return redirect("/students")->with("success","$student->name has been edited!");
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Student  $student
     * @return \Illuminate\Http\Response
     */
    public function destroy(Student $student)
    {

        //Cascade Effect on Presences
        $presences = DB::table("presences")
        ->join("students","presences.studentId","=","students.studentId")
        ->where("students.studentId",$student->studentId)
        ->select('presences.id')
        ->get();
        foreach($presences as $presence){
            $p = Presence::findOrFail($presence->id);
            $p->delete();
        }
        $student->delete();
        return redirect('/students')->with("success","$student->name has been deleted!");
    }
}
