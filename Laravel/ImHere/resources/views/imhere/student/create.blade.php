@extends('layouts.card')
@section('card-title')
  @if (isset($student))
      Edit Student
  @else
      Add Student
  @endif
@endsection
@section('card-body')

    @if (isset($student))
        <form action="/students/{{$student->id}}" method="POST">
            @method("PUT")
            <div class="form-group">
                @csrf
                
                <label for="name">Name</label>
                <input type="text" class="form-group" name="name" value={{$student->name}}>
                <label for="id">Surname</label>
                <input type="text" class="form-group" name="surname" value={{$student->surname}}>
                <label for="studentId">Id</label>
                <input type="number" class="form-group" name="studentId" value={{$student->idStudent}}>
            </div>
            <button type="submit" class="btn btn-primary">Update</button>
        </form>
        
    @else
        <form action="/students" method="POST">
        
            <div class="form-group">
                @csrf
                <label for="name">Name</label>
                <input type="text" class="form-group" name="name" placeholder="name of the student">
                <label for="id">Surname</label>
                <input type="text" class="form-group" name="surname" placeholder="surname of the student">
                <label for="studentId">Id</label>
                <input type="number" class="form-group" name="studentId" placeholder="id of the student">
            </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    @endif
    
@endsection