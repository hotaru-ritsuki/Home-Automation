export class Rule {
  constructor(
    public id: number,
    public name: string,
    public description: string,
    public active: string,
    public condition: string,
    public actionRule: string
  ) {
  }
}
