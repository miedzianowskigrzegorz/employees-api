import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeListComponent } from './employee/employee-list/employee-list.component';
 import { EmployeeFormComponent } from './employee/employee-form/employee-form.component';
 import { EmployeeEditComponent } from './employee/employee-edit/employee-edit.component';

const routes: Routes = [

  { path: 'employees', component: EmployeeListComponent },
  { path: 'addEmployee', component: EmployeeFormComponent },
  { path: 'employees/:id/edit', component: EmployeeEditComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
