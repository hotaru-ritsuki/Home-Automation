export class Rule {
  constructor(
    public id: number,
    public name: string,
    public description: string,
    public active: boolean,
    public conditions: string,
    public actionRule: {}
  ) {
  }
}
