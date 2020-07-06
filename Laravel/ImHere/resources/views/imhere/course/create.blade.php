@extends('layouts.card')
@section('card-title')
  @if (isset($course))
      Edit Course
  @else
      Add Course
  @endif
@endsection
@section('card-body')
    @if (isset($course))
        <form action="/courses/{{$course->id}}" method="POST">
            @method("PUT")
            <div class="form-group">
                @csrf
                <label for="name">Name</label>
                <input type="text" class="form-group" name="name" value={{$course->name}}>       
                <label for="slug">Slug</label>
                <input type="text" class="form-group" name="slug" value={{$course->slug}}>

            </div>
            <button type="submit" class="btn btn-primary">Update</button>
        </form>
        
    @else
        <form action="/courses" method="POST">
        
            <div class="form-group">
                @csrf
                <label for="name">Name</label>
                <input type="text" class="form-group" name="name" placeholder="name of the course">
                <label for="slug">Slug</label>
                <input type="text" class="form-group" name="slug" placeholder="Slug of the course">
            </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    @endif
    
@endsection