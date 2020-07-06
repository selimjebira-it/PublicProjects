<?php

namespace App\Http\Controllers;

use App\Course;
use Illuminate\Http\Request;

class CourseController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $courses = Course::all();
        return view("imhere.course.courses",compact("courses"));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('imhere.course.create');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $validationData = $request->validate([
            'slug'=>'required|unique:courses|max:3',
            'name'=>'required|max:20',
        ]);
        $course = Course::create($request->all());
        return redirect("/courses")->with("success","course has been added!");
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Course  $course
     * @return \Illuminate\Http\Response
     */
    public function show(Course $course)
    {
        return view("imhere.course.show",compact("course"));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Course  $course
     * @return \Illuminate\Http\Response
     */
    public function edit(Course $course)
    {
        return view("imhere.course.create",compact("course"));
        
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Course  $course
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, Course $course)
    {
        if($course->slug != $request->get('slug')){
            $validationData = $request->validate([
                'slug'=>'required|unique:courses|max:3',
                'name'=>'required|max:20',
            ]);
            $course->update($request->all());
        }else{
            $validationData = $request->validate([
                'name' => 'required|max:20'
            ]);
            $course->update($request->all());
        }
        return redirect("/courses")->with("success","course has been edited!");
        
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Course  $course
     * @return \Illuminate\Http\Response
     */
    public function destroy(Course $course)
    {
        $course->delete();
        return redirect("/courses")->with("success","course has been deleted!");
        
    }
}
