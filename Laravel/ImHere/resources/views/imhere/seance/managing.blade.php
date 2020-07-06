@extends('layouts.card')

@section('card-title')
    Presence Management for {{$seance->slug}} number {{$seance->seanceNb}} of {{$seance->date}} at {{$seance->hour}} duration: {{($seance->duration)}}
@endsection

@section('card-body')

<form action="/presences" method="POST" name="presenceForm">
<table class="table">
    <thead>
        <tr>
            <th>Name</th>
            <th>SurName</th>
            <th>ID</th>
            <th></th>
        </tr>
    </thead>
   
    <tbody>
        
        <input type="hidden"  name="seanceId" value="{{$seance->id}}">
        @foreach ($students as $student)
                <tr>
                    <td>
                        {{$student->name}}
                    </td>
                    <td>
                        {{$student->surname}}
                    </td>
                    <td>
                        {{$student->studentId}}
                    </td>
                    <td>
                    <input type="checkbox"  class="form-group" name="studentIds[]" value={{$student->id}}>
                    </td>
                </tr>
       @endforeach
    </tbody>  
        @csrf
        <button class="btn btn-primary" type="submit">Submit</button>
</table>
</form>





@endsection